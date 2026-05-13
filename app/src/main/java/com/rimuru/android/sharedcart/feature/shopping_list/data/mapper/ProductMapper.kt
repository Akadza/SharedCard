package com.rimuru.android.sharedcart.feature.shopping_list.data.mapper

import com.rimuru.android.sharedcart.feature.shopping_list.data.local.ProductEntity
import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.Product

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