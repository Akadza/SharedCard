package com.rimuru.android.sharedcart.domain.model

data class ShoppingList (
    val id: String,
    val ownerId: String,
    val name: String,
    val products: List<Product> = emptyList()
)