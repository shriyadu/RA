package com.example.recipeapp.ui.feature.recipeDetail.network

import com.example.recipeapp.ui.feature.recipeDetail.model.RecipeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeDetailApiService {

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetail(
        @Path("id") recipeId: String,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = true
    ): RecipeDetailResponse
}
