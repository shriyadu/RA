package com.example.recipeapp.ui.feature.Search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.ui.feature.Search.data.model.RecipeSearchSuggestion
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _suggestions = MutableStateFlow<List<RecipeSearchSuggestion>>(emptyList())
    val suggestions: StateFlow<List<RecipeSearchSuggestion>> = _suggestions

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _query.debounce(300).collectLatest { currentQuery ->
                if (currentQuery.isBlank()) {
                    _suggestions.value = emptyList()
                } else {
                    _isLoading.value = true
                    _suggestions.value = repository.getAutoComplete(currentQuery)
                    _isLoading.value = false
                }
            }
        }
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }
}
