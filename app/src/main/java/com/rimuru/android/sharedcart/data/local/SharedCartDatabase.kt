package com.rimuru.android.sharedcart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rimuru.android.sharedcart.data.local.converter.DateConverter
import com.rimuru.android.sharedcart.data.local.dao.ProductDao
import com.rimuru.android.sharedcart.data.local.dao.ShoppingListDao
import com.rimuru.android.sharedcart.data.local.entity.ProductEntity
import com.rimuru.android.sharedcart.data.local.entity.ShoppingListEntity

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