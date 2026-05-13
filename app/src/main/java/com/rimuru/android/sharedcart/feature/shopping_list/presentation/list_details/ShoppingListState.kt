package com.rimuru.android.sharedcart.feature.shopping_list.presentation.list_details

import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList

data class ShoppingListState (
    val lists: List<ShoppingList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)