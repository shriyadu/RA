package com.example.recipeapp.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val readyInMinutes: Int
)
