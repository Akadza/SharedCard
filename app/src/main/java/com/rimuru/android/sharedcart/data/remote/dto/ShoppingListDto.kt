package com.rimuru.android.sharedcart.data.remote.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class ShoppingListDto (
    val id: String = "",
    val name: String = "",
    val ownerId: String = "",
    @ServerTimestamp val createdAt: Timestamp? = null
)