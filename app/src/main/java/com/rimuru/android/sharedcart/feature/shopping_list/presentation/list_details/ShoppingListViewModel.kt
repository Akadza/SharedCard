package com.rimuru.android.sharedcart.feature.shopping_list.presentation.list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rimuru.android.sharedcart.feature.shopping_list.domain.model.ShoppingList
import com.rimuru.android.sharedcart.feature.shopping_list.domain.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingListState())
    val state: StateFlow<ShoppingListState> = _state.asStateFlow()

    init {
        repository.observeRemoteLists(ownerId = "current_user_id")
        loadShoppingLists()
    }

    private fun loadShoppingLists() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            repository.getAllLists()
                .catch { e ->
                    _state.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                }
                .collect { lists ->
                    _state.update { it.copy(
                        lists = lists,
                        isLoading = false
                    ) }
                }
        }
    }

    fun addList(name: String) {
        viewModelScope.launch {
            val newList = ShoppingList(
                id = UUID.randomUUID().toString(),
                ownerId = "current_user_id",
                name = name,
                createdAt = Instant.now()
            )
            repository.saveList(newList)
        }
    }

    fun deleteList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.deleteList(shoppingList)
        }
    }
}