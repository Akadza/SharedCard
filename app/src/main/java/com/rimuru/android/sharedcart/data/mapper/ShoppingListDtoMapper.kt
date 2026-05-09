package com.rimuru.android.sharedcart.data.mapper

import com.rimuru.android.sharedcart.data.local.entity.ShoppingListEntity
import com.rimuru.android.sharedcart.data.remote.dto.ShoppingListDto
import java.time.Instant

fun ShoppingListDto.toEntity() : ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        ownerId = ownerId,
        name = name,
        createdAt = createdAt?.toDate()?.toInstant() ?: Instant.now()
    )
}