package com.example.recipeapp.core.di

import com.example.recipeapp.core.network.RetrofitClient
import com.example.recipeapp.ui.feature.dashboard.network.RecipeApiService
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeApiService(): RecipeApiService = RetrofitClient.create()

    @Provides
    @Singleton
    fun provideRecipeRepository(
        apiService: RecipeApiService
    ): RecipeRepository = RecipeRepository(apiService, "d208062c7d764651a9209081a69ceaab")
}
