package com.example.recipeapp.ui.feature.recipeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.core.data.local.FavoriteRecipeEntity
import com.example.recipeapp.ui.feature.recipeDetail.model.RecipeDetail
import com.example.recipeapp.ui.feature.recipeDetail.repository.RecipeDetailRepository
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailVM @Inject constructor(
    private val repository: RecipeDetailRepository,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipeDetail = MutableStateFlow<RecipeDetail?>(null)
    val recipeDetail: StateFlow<RecipeDetail?> = _recipeDetail.asStateFlow()

    fun loadRecipeDetail(recipeId: String) {
        viewModelScope.launch {
            try {
                val detail = repository.getRecipeDetail(recipeId)
                _recipeDetail.value = detail
            } catch (e: Exception) {
                _recipeDetail.value = null
            }
        }
    }

    suspend fun isFavorite(recipeId: Int): Boolean = recipeRepository.isFavorite(recipeId)

    suspend fun addFavorite(favorite: FavoriteRecipeEntity) = recipeRepository.addFavorite(favorite)

    suspend fun removeFavorite(favorite: FavoriteRecipeEntity) = recipeRepository.removeFavorite(favorite)
}
