package com.example.recipeapp.ui.feature.dashboard.network

import com.example.recipeapp.ui.feature.dashboard.model.AllRecipesResponse
import com.example.recipeapp.ui.feature.dashboard.model.RandomRecipesResponse
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
}
