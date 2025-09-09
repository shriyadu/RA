package com.example.recipeapp.core.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteRecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteRecipeEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteRecipeEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :recipeId)")
    suspend fun isFavorite(recipeId: Int): Boolean
}
