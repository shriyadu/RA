package com.example.recipeapp.ui.feature.Favourite

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.ui.feature.dashboard.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteVM(application: Application) : AndroidViewModel(application) {

    private val sharedPref = application.getSharedPreferences("fav_prefs", Context.MODE_PRIVATE)
    private val FAVORITES_KEY = "favorite_recipes"

    // To store favorite recipe IDs
    private val _favoriteIds = mutableSetOf<String>()

    // To expose favorites as a StateList for UI recomposition
    val favorites = mutableStateListOf<Recipe>()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        val savedIds = sharedPref.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
        _favoriteIds.clear()
        _favoriteIds.addAll(savedIds)
        // Optionally convert IDs to Recipe objects if you have access to recipe data here
        // For example, fetch from repository or cache by IDs and add to favorites list
    }

//    fun toggleFavorite(recipe: Recipe) {
//        viewModelScope.launch(Dispatchers.IO) {
//            //if (_favoriteIds.contains(recipe.id)) {
//               // _favoriteIds.remove(recipe.id)
//                // Temporarily comment out the problematic line:
//                // favorites.remove(recipe)
//                val itemToRemove = favorites.find { it.id == recipe.id } // Find by ID
//                itemToRemove?.let { favorites.remove(it) } // Remove if found
//            } else {
//               // _favoriteIds.add(recipe.id)
//                favorites.add(recipe)
//            }
//            saveFavorites()
//        }
//    }

    private fun saveFavorites() {
        sharedPref.edit().putStringSet(FAVORITES_KEY, _favoriteIds).apply()
    }

    fun isFavorite(recipeId: String) = _favoriteIds.contains(recipeId)
}
