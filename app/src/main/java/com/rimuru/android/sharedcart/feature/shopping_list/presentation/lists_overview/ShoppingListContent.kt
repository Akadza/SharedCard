package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShoppingListContent (
    state: ShoppingListState,
    onEvent: (ShoppingListEvent) -> Unit
) {
    Text(text = "${state.lists.size}")
}