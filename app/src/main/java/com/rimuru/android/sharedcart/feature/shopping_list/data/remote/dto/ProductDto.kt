package com.rimuru.android.sharedcart.feature.shopping_list.data.remote.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class ProductDto (
    val id: String = "",
    val listId: String = "",
    val name: String = "",
    @field:JvmField val isCompleted: Boolean = false,
    @ServerTimestamp val lastUpdate: Timestamp? = null
)