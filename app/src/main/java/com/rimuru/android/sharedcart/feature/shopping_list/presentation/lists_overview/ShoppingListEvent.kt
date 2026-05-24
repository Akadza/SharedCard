package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList

sealed interface ShoppingListEvent {
    data object AddListClicked: ShoppingListEvent
    data object AddListDialogDismissed: ShoppingListEvent
    data object CreateListConfirmed: ShoppingListEvent
    data object ErrorShown: ShoppingListEvent

    data class ListNameChanged(val name: String): ShoppingListEvent
    data class DeleteListClicked(val list: ShoppingList): ShoppingListEvent
    data class ListClicked(val listId: String): ShoppingListEvent
}