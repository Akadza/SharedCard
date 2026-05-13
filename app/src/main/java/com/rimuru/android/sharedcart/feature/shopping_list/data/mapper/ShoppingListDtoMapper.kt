package com.rimuru.android.sharedcart.feature.shopping_list.data.mapper

import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ShoppingListEntity
import com.rimuru.android.sharedcart.feature.shopping_list.data.remote.dto.ShoppingListDto
import java.time.Instant

fun ShoppingListDto.toEntity() : ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        ownerId = ownerId,
        name = name,
        createdAt = createdAt?.toDate()?.toInstant() ?: Instant.now()
    )
}