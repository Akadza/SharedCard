package com.rimuru.android.sharedcart.domain.repository

import com.rimuru.android.sharedcart.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow


interface ShoppingRepository {
    fun getAllLists(): Flow<List<ShoppingList>>
    suspend fun saveList(shoppingList: ShoppingList)
    suspend fun deleteList(shoppingList: ShoppingList)
}