package com.rimuru.android.sharedcart.domain.model

import java.time.Instant

data class Product(
    val id: String,
    val listId: String,
    val name: String,
    val lastUpdate: Instant,
    val isCompleted: Boolean
)