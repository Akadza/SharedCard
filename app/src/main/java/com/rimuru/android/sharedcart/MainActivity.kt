package com.rimuru.android.sharedcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rimuru.android.sharedcart.core.ui.theme.SharedCartTheme
import com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview.ShoppingListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedCartTheme {
                ShoppingListScreen()
            }
        }
    }
}