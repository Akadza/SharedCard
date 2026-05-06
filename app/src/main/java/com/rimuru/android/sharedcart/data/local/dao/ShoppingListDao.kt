package com.rimuru.android.sharedcart.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rimuru.android.sharedcart.data.local.entity.ShoppingListEntity
import com.rimuru.android.sharedcart.data.local.entity.ShoppingListWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_list ORDER BY created_at")
    fun getAllShoppingList(): Flow<List<ShoppingListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getListWithProducts(): Flow<List<ShoppingListWithProducts>>
}