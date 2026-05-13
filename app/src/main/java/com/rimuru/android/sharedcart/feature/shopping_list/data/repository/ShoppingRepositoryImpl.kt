package com.rimuru.android.sharedcart.feature.shopping_list.data.repository

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ShoppingListDao
import com.rimuru.android.sharedcart.feature.shopping_list.data.mapper.toDomain
import com.rimuru.android.sharedcart.feature.shopping_list.data.mapper.toEntity
import com.rimuru.android.sharedcart.feature.shopping_list.data.remote.dto.ShoppingListDto
import com.rimuru.android.sharedcart.feature.shopping_list.domain.repository.ShoppingRepository
import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao,
    private val firestore: FirebaseFirestore,
    private val externalScope: CoroutineScope
): ShoppingRepository {

    override fun getAllLists(): Flow<List<ShoppingList>> {
        return shoppingListDao.getAllShoppingList()
            .map { entities ->
                entities.map { it.toDomain() }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun saveList(shoppingList: ShoppingList) {
        withContext(Dispatchers.IO) {
            try {
                // Room
                shoppingListDao.insertShoppingList(shoppingList.toEntity())

                // Firebase
                val listDto = ShoppingListDto(
                    id = shoppingList.id,
                    name = shoppingList.name,
                    ownerId = shoppingList.ownerId
                )
                firestore.collection("shopping_lists")
                    .document(listDto.id)
                    .set(listDto)
                    .await()
            } catch (e: Exception) {
                Log.e("ShoppingRepository", "Error saving list: ${e.message}")
            }
        }
    }

    override suspend fun deleteList(shoppingList: ShoppingList) {
        withContext(Dispatchers.IO) {
            try {
                // Room
                shoppingListDao.deleteShoppingList(shoppingList.toEntity())
                // Firebase
                firestore.collection("shopping_lists")
                    .document(shoppingList.id)
                    .delete()
                    .await()
            } catch (e: Exception) {
                Log.e("ShoppingRepository", "Error deleting list: ${e.message}")
            }
        }
    }

    override fun observeRemoteLists(ownerId: String) {
        firestore.collection("shopping_lists")
            .whereEqualTo("ownerId", ownerId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) return@addSnapshotListener

                snapshot?.documentChanges?.forEach { change ->
                    val dto = change.document.toObject(ShoppingListDto::class.java)
                    externalScope.launch(Dispatchers.IO) {
                        try {
                            when (change.type) {
                                DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> {
                                    shoppingListDao.insertShoppingList(dto.toEntity())
                                }
                                DocumentChange.Type.REMOVED -> {
                                    shoppingListDao.deleteShoppingList(dto.toEntity())
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("ShoppingRepository", "Error syncing remote changes ${e.message}")
                        }
                    }
                }
            }
    }
}