package com.example.recipeapp.ui.feature.dashboard.network


import com.example.recipeapp.core.data.local.AllRecipesResponse
import com.example.recipeapp.core.data.local.RandomRecipesResponse
import com.example.recipeapp.ui.feature.Search.data.model.RecipeSearchResponse
import com.example.recipeapp.ui.feature.Search.data.model.RecipeSearchSuggestion
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("recipes/random")
    suspend fun getPopularRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): RandomRecipesResponse

    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): AllRecipesResponse
    @GET("recipes/autocomplete")
    suspend fun autoComplete(
        @Query("query") query: String,
        @Query("number") number: Int = 5,
        @Query("apiKey") apiKey: String // Put your API key here or via Interceptor
    ): List<RecipeSearchSuggestion>

    // For searching recipes by query
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Int = 10,
        @Query("apiKey") apiKey: String, // Or use Interceptor
        @Query("addRecipeInformation") addInfo: Boolean = true
    ): RecipeSearchResponse
}
