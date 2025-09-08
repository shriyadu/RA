package com.example.recipeapp.ui.feature.dashboard.repository

import com.example.recipeapp.ui.feature.dashboard.network.RecipeApiService

class RecipeRepository(
    private val apiService: RecipeApiService,
    private val apiKey: String
) {
    suspend fun getPopularRecipes(number: Int) = apiService.getPopularRecipes(number, apiKey).recipes
    suspend fun getAllRecipes(number: Int) = apiService.getAllRecipes(number, apiKey).results
}
