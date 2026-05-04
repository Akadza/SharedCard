package com.rimuru.android.sharedcart.data.mapper

import com.rimuru.android.sharedcart.data.local.entity.ProductEntity
import com.rimuru.android.sharedcart.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        listId = listId,
        name = name,
        isCompleted = isCompleted,
        lastUpdate = lastUpdate
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        listId = listId,
        name = name,
        isCompleted = isCompleted,
        lastUpdate = lastUpdate
    )
}