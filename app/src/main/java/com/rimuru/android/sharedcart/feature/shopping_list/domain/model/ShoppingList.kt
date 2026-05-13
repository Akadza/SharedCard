package com.rimuru.android.sharedcart.feature.shopping_list.domain.model

import java.time.Instant

data class ShoppingList (
    val id: String,
    val ownerId: String,
    val name: String,
    val createdAt: Instant,
    val products: List<Product> = emptyList()
)