package com.example.recipeapp.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?
)
data class RandomRecipesResponse(val recipes: List<RecipeEntity>)

data class AllRecipesResponse(val results: List<RecipeEntity>)
