package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rimuru.android.sharedcart.core.ui.theme.SharedCartTheme

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ShoppingListContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}