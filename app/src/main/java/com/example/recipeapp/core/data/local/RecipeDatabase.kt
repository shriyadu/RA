package com.example.recipeapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.ui.feature.recipeDetail.model.RecipeDetailEntity
import com.example.recipeapp.ui.feature.recipeDetail.data.local.RecipeDetailDao

@Database(entities = [RecipeEntity::class, RecipeDetailEntity::class, FavoriteRecipeEntity::class], version = 3)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailDao(): RecipeDetailDao
    abstract fun favoriteDao(): FavoriteDao
}
