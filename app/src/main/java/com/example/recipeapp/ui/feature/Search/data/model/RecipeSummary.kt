package com.example.recipeapp.ui.feature.Search.data.model



data class RecipeSummary(
    val id: Int,
    val title: String,
    val image: String?
)

data class RecipeSearchResponse(
    val results: List<RecipeSummary>
)
