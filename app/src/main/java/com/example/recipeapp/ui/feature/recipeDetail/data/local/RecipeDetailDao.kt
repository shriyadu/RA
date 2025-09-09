package com.example.recipeapp.ui.feature.recipeDetail.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.ui.feature.recipeDetail.model.RecipeDetailEntity


@Dao
interface RecipeDetailDao {
    @Query("SELECT * FROM recipe_details WHERE id = :id LIMIT 1")
    suspend fun getRecipeDetail(id: String): RecipeDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDetail(recipeDetailEntity: RecipeDetailEntity)
}
