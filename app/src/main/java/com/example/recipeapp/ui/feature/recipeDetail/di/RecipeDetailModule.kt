package com.example.recipeapp.ui.feature.recipeDetail.di

import android.content.Context
import com.example.recipeapp.core.data.local.RecipeDatabase
import com.example.recipeapp.core.network.RetrofitClient
import com.example.recipeapp.ui.feature.recipeDetail.data.local.RecipeDetailDao
import com.example.recipeapp.ui.feature.recipeDetail.network.RecipeDetailApiService
import com.example.recipeapp.ui.feature.recipeDetail.repository.RecipeDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object RecipeDetailModule {

    @Provides
    fun provideRecipeDetailApiService(): RecipeDetailApiService = RetrofitClient.create()

    @Provides
    fun provideRecipeDetailDao(database: RecipeDatabase): RecipeDetailDao = database.recipeDetailDao()

    @Provides
    fun provideRecipeDetailRepository(
        apiService: RecipeDetailApiService,
        recipeDetailDao: RecipeDetailDao,
        @ApplicationContext context: Context
    ): RecipeDetailRepository =
        RecipeDetailRepository(apiService, "0f8af2c443294dbba5e46241994832c4", recipeDetailDao, context)
}
