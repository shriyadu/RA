package com.example.recipeapp.ui.feature.dashboard.repository
import android.content.Context
import com.example.recipeapp.core.data.local.FavoriteDao
import com.example.recipeapp.core.data.local.FavoriteRecipeEntity
import com.example.recipeapp.core.data.local.RecipeDao
import com.example.recipeapp.core.utils.NetworkUtils
import com.example.recipeapp.core.data.local.RecipeEntity
import com.example.recipeapp.ui.feature.Search.data.model.RecipeSearchSuggestion
import com.example.recipeapp.ui.feature.Search.data.model.RecipeSummary
import com.example.recipeapp.ui.feature.dashboard.network.RecipeApiService

class RecipeRepository(
    private val apiService: RecipeApiService,
    private val apiKey: String,
    private val recipeDao: RecipeDao,
    private val favoriteDao: FavoriteDao,
    private val context: Context
) {

    suspend fun getPopularRecipes(number: Int): List<RecipeEntity> {
        return if (NetworkUtils.isInternetAvailable(context)) {
            val recipes = apiService.getPopularRecipes(number, apiKey).recipes
            // Cache to Room
            recipeDao.insertRecipes(recipes)
            recipes
        } else {
            // Load from Room and convert back to domain model
            recipeDao.getRecipes(number).map { entityToRecipe(it) }
        }
    }

    suspend fun getAllRecipes(number: Int): List<RecipeEntity> {
        return if (NetworkUtils.isInternetAvailable(context)) {
            val recipes = apiService.getAllRecipes(number, apiKey).results
            recipeDao.insertRecipes(recipes)
            recipes
        } else {
            recipeDao.getRecipes(number).map { entityToRecipe(it) }
        }
    }

    suspend fun getAutoComplete(query: String, number: Int = 5): List<RecipeSearchSuggestion> {
        return apiService.autoComplete(query, number, apiKey)
    }

    suspend fun searchRecipes(query: String, number: Int = 10): List<RecipeSummary> {
        return apiService.searchRecipes(query, number, apiKey).results
    }
    private fun entityToRecipe(entity: RecipeEntity): RecipeEntity {
        return RecipeEntity(
            id = entity.id,
            title = entity.title,
            image = entity.image,
            readyInMinutes = entity.readyInMinutes
        )
    }
    fun getFavorites() = favoriteDao.getAllFavorites()

    suspend fun addFavorite(favorite: FavoriteRecipeEntity) = favoriteDao.insertFavorite(favorite)

    suspend fun removeFavorite(favorite: FavoriteRecipeEntity) = favoriteDao.deleteFavorite(favorite)

    suspend fun isFavorite(recipeId:Int) = favoriteDao.isFavorite(recipeId)

}
