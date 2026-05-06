package com.rimuru.android.sharedcart.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rimuru.android.sharedcart.domain.model.Product
import java.time.Instant

@Entity(tableName = "shopping_list")
data class ShoppingListEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: Instant,
    val name: String,
    val products: List<Product> = emptyList()
)