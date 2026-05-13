package com.rimuru.android.sharedcart.feature.shopping_list.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "shopping_list")
data class ShoppingListEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: Instant,
    val name: String,
)