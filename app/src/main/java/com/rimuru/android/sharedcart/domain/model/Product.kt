package com.rimuru.android.sharedcart.domain.model

data class Product(
    val id: String,
    val listId: String,
    val name: String,
    val lastUpdate: Long,
    val isCompleted: Boolean
)