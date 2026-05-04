package com.rimuru.android.sharedcart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rimuru.android.sharedcart.data.local.converter.ProductConverter
import com.rimuru.android.sharedcart.data.local.dao.ProductDao
import com.rimuru.android.sharedcart.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductConverter::class)
abstract class SharedCartDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}