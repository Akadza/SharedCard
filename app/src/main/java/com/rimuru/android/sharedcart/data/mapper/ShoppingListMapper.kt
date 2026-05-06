package com.rimuru.android.sharedcart.data.mapper

import com.rimuru.android.sharedcart.data.local.entity.ShoppingListEntity
import com.rimuru.android.sharedcart.domain.model.ShoppingList

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