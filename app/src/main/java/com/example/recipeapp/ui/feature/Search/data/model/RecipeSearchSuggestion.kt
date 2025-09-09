package com.example.recipeapp.ui.feature.Search.data.model



data class RecipeSearchSuggestion(
    val id: Int,
    val title: String,
    val imageType: String? = null // sometimes not provided
)
