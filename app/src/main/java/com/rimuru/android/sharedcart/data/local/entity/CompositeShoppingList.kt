package com.rimuru.android.sharedcart.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

// data transfer object (dto)
data class CompositeShoppingList (
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "listId"
    )
    val products: List<ProductEntity>
)