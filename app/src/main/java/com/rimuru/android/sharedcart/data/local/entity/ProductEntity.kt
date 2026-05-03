package com.rimuru.android.sharedcart.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey val id: String,
    val listId: String,
    val name: String,
    val lastUpdate: Instant,
    val isCompleted: Boolean
)