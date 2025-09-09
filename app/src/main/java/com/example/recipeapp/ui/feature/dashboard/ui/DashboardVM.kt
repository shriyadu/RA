package com.example.recipeapp.ui.feature.dashboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.core.data.local.RecipeEntity

import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _popularRecipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val popularRecipes: StateFlow<List<RecipeEntity>> = _popularRecipes

    private val _allRecipes = MutableStateFlow<List<RecipeEntity>>(emptyList())
    val allRecipes: StateFlow<List<RecipeEntity>> = _allRecipes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _popularRecipes.value = repository.getPopularRecipes(5)
                _allRecipes.value = repository.getAllRecipes(15)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
