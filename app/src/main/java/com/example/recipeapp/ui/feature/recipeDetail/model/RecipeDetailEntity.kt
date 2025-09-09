package com.example.recipeapp.ui.feature.recipeDetail.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_details")
data class RecipeDetailEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String?,
    val readyInMinutes: Int,
    val servings: Int,
    val pricePerServing: String,
    val instructions: String,
    val quickSummary: String
)
