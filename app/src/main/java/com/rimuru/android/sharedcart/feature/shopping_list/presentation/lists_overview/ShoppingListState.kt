package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList

data class ShoppingListState (
    val lists: List<ShoppingList> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isAddDialogVisible: Boolean = false,
    val newListName: String = ""
)