package com.rimuru.android.sharedcart.feature.shopping_list.data.local

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