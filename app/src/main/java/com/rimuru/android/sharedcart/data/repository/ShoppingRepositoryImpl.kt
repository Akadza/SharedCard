package com.rimuru.android.sharedcart.data.repository

import com.rimuru.android.sharedcart.data.local.dao.ShoppingListDao
import com.rimuru.android.sharedcart.data.mapper.toDomain
import com.rimuru.android.sharedcart.data.mapper.toEntity
import com.rimuru.android.sharedcart.domain.model.ShoppingList
import com.rimuru.android.sharedcart.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingListDao: ShoppingListDao
): ShoppingRepository {

    override fun getAllLists(): Flow<List<ShoppingList>> {
        return shoppingListDao.getAllShoppingList().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveList(shoppingList: ShoppingList) {
        shoppingListDao.insertShoppingList(shoppingList.toEntity())
    }

    override suspend fun deleteList(shoppingList: ShoppingList) {
        shoppingListDao.deleteShoppingList(shoppingList.toEntity())
    }
}