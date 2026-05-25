package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rimuru.android.sharedcart.core.ui.theme.SharedCartTheme
import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList
import com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview.components.AddListDialog
import java.time.Instant

@Composable
fun ShoppingListContent (
    state: ShoppingListState,
    onEvent: (ShoppingListEvent) -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "${state.lists.size}")
    Button(
        onClick = { onEvent(ShoppingListEvent.AddListClicked) },
        modifier = Modifier.padding(50.dp)
    ) {
        Text("Add Cart")
    }
    if (state.isAddDialogVisible) {
        AddListDialog(
            name = state.newListName,
            errorMessage = state.errorMessage,
            onNameChanged = { newName -> onEvent(ShoppingListEvent.ListNameChanged(newName)) },
            onDismissRequest = { onEvent(ShoppingListEvent.AddListDialogDismissed) },
            onConfirmation = { onEvent(ShoppingListEvent.CreateListConfirmed) }
        )
    }
}

@Preview
@Composable
fun ShoppingListContentPreview() {
    val shoppingState = ShoppingListState(
        lists = List(10) { i ->
            ShoppingList(
                id = "1",
                ownerId = "user",
                name = "Cart",
                createdAt = Instant.now(),
                products = emptyList(),
            )
        }
    )
    SharedCartTheme {
        ShoppingListContent(shoppingState) { event -> }
    }
}

