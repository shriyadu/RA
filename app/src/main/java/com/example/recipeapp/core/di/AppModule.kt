package com.example.recipeapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.recipeapp.core.data.local.*
import com.example.recipeapp.core.network.RetrofitClient
import com.example.recipeapp.ui.feature.dashboard.network.RecipeApiService
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatabase(@ApplicationContext context: Context): RecipeDatabase =
        Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_database")
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideRecipeDao(db: RecipeDatabase): RecipeDao = db.recipeDao()

    @Provides
    fun provideFavoriteDao(db: RecipeDatabase): FavoriteDao = db.favoriteDao()

    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: RecipeApiService,
        recipeDao: RecipeDao,
        favoriteDao: FavoriteDao,
        @ApplicationContext context: Context
    ): RecipeRepository = RecipeRepository(api, "0f8af2c443294dbba5e46241994832c4", recipeDao, favoriteDao, context)
}
