package com.example.recipeapp.ui.feature.recipeDetail.model

data class RecipeDetail(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val readyInMinutes: Int,
    val servings: Int,
    val pricePerServing: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val equipments: List<Equipment>,
    val quickSummary: String,
    var isUserFavorite: Boolean = false
)

data class Ingredient(
    val name: String,
    val imageUrl: String?
)

data class Equipment(
    val name: String,
    val imageUrl: String?
)
