package com.example.recipeapp.ui.feature.dashboard.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?
)

data class RandomRecipesResponse(val recipes: List<Recipe>)
data class AllRecipesResponse(val results: List<Recipe>)
