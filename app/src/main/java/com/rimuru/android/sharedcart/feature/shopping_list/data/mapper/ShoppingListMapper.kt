package com.rimuru.android.sharedcart.feature.shopping_list.data.mapper

import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ShoppingListEntity
import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList

fun ShoppingListEntity.toDomain(): ShoppingList {
    return ShoppingList(
        id = id,
        ownerId = ownerId,
        name = name,
        createdAt = createdAt,
    )
}

fun ShoppingList.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        ownerId = ownerId,
        name = name,
        createdAt = createdAt,
    )
}