package com.rimuru.android.sharedcart.feature.shopping_list.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_list ORDER BY created_at")
    fun getAllShoppingList(): Flow<List<ShoppingListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lists: List<ShoppingListEntity>)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getListWithProducts(): Flow<List<CompositeShoppingList >>
}