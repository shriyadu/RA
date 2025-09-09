package com.example.recipeapp.ui.feature.recipeDetail.repository

import android.content.Context
import com.example.recipeapp.core.utils.NetworkUtils
import com.example.recipeapp.ui.feature.recipeDetail.data.local.RecipeDetailDao
import com.example.recipeapp.ui.feature.recipeDetail.model.RecipeDetail
import com.example.recipeapp.ui.feature.recipeDetail.network.RecipeDetailApiService
import com.example.recipeapp.ui.feature.recipeDetail.network.toDomainModel
import com.example.recipeapp.ui.feature.recipeDetail.network.toRecipeDetailModel
import com.example.recipeapp.ui.feature.recipeDetail.network.toEntity

class RecipeDetailRepository(
    private val apiService: RecipeDetailApiService,
    private val apiKey: String,
    private val recipeDetailDao: RecipeDetailDao,
    private val context: Context
) {

    suspend fun getRecipeDetail(recipeId: String): RecipeDetail {
        return if (NetworkUtils.isInternetAvailable(context)) {
            val response = apiService.getRecipeDetail(recipeId, apiKey)
            val detail = response.toRecipeDetailModel()
            recipeDetailDao.insertRecipeDetail(detail.toEntity())
            detail
        } else {
            recipeDetailDao.getRecipeDetail(recipeId)?.toDomainModel()
                ?: throw Exception("No cached data available")
        }
    }
}
