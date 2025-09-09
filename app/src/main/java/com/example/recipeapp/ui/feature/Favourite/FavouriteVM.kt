package com.example.recipeapp.feature.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.core.data.local.FavoriteRecipeEntity

import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavouriteVM @Inject constructor(
    repository: RecipeRepository
) : ViewModel() {
    val favorites = repository.getFavorites().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )
}
