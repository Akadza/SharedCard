package com.rimuru.android.sharedcart.feature.shopping_list.presentation.lists_overview

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

    fun onEvent(event: ShoppingListEvent) {
        when(event) {
            ShoppingListEvent.AddListClicked -> {
                _state.update { it.copy(isAddDialogVisible = true) }
            }
            ShoppingListEvent.AddListDialogDismissed -> {
                _state.update {
                    it.copy(
                        isAddDialogVisible = false,
                        newListName = "",
                        errorMessage = null
                    )
                }
            }
            ShoppingListEvent.CreateListConfirmed -> {
                val name = state.value.newListName.trim()
                if (name.isBlank()) {
                    _state.update {
                        it.copy(
                            errorMessage = "the list name cannot be empty"
                        )
                    }
                    return
                }
                createList(name)
            }
            ShoppingListEvent.ErrorShown -> {
                _state.update { it.copy(errorMessage = null) }
            }

            is ShoppingListEvent.ListClicked -> Unit
            is ShoppingListEvent.ListNameChanged -> {
                _state.update {
                    it.copy(
                        newListName = event.name,
                        errorMessage = null
                    )
                }
            }
            is ShoppingListEvent.DeleteListClicked -> {
                deleteList(event.list)
            }
        }
    }

    private fun loadShoppingLists() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            repository.getAllLists()
                .catch { e ->
                    _state.update { it.copy(
                        errorMessage = e.message,
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

    private fun createList(name: String) {
        _state.update {
            it.copy(
                isAddDialogVisible = false,
                newListName = "",
                errorMessage = null
            )
        }
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

    private fun deleteList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.deleteList(shoppingList)
        }
    }
}