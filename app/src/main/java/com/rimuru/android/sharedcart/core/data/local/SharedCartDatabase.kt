package com.rimuru.android.sharedcart.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rimuru.android.sharedcart.core.data.local.converters.DateConverter
import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ProductDao
import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ShoppingListDao
import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ProductEntity
import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ShoppingListEntity

@Database(
    entities = [ProductEntity::class, ShoppingListEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class SharedCartDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun shoppingListDao(): ShoppingListDao
}