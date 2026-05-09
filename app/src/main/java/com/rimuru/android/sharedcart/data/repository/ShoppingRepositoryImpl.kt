package com.rimuru.android.sharedcart.data.repository

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.rimuru.android.sharedcart.data.local.dao.ShoppingListDao
import com.rimuru.android.sharedcart.data.mapper.toDomain
import com.rimuru.android.sharedcart.data.mapper.toEntity
import com.rimuru.android.sharedcart.data.remote.dto.ShoppingListDto
import com.rimuru.android.sharedcart.domain.model.ShoppingList
import com.rimuru.android.sharedcart.domain.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao,
    private val firestore: FirebaseFirestore,
    private val externalScope: CoroutineScope
): ShoppingRepository {

    override fun getAllLists(): Flow<List<ShoppingList>> {
        return shoppingListDao.getAllShoppingList().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveList(shoppingList: ShoppingList) {
        // Room
        shoppingListDao.insertShoppingList(shoppingList.toEntity())
        // Firebase
        try {
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

        }
    }

    override suspend fun deleteList(shoppingList: ShoppingList) {
        // Room
        shoppingListDao.deleteShoppingList(shoppingList.toEntity())
        // Firebase
        firestore.collection("shopping_lists").document(shoppingList.id).delete()

    }

    override fun observeRemoteLists(ownerId: String) {
        firestore.collection("shopping_lists")
            .whereEqualTo("ownerId", ownerId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) return@addSnapshotListener

                snapshot?.documentChanges?.forEach { change ->
                    val dto = change.document.toObject(ShoppingListDto::class.java)
                    externalScope.launch {
                        when (change.type) {
                            DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> {
                                shoppingListDao.insertShoppingList(dto.toEntity())
                            }
                            DocumentChange.Type.REMOVED -> {
                                shoppingListDao.deleteShoppingList(dto.toEntity())
                            }
                        }
                    }
                }
            }
    }
}