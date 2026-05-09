package com.rimuru.android.sharedcart.data.remote.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class ProductDto (
    val id: String = "",
    val name: String = "",
    @field:JvmField val isCompleted: Boolean = false,
    @ServerTimestamp val lastUpdate: Timestamp? = null
)