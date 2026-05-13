package com.rimuru.android.sharedcart.presentation.shopping_list

import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList

data class ShoppingListState (
    val lists: List<ShoppingList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)