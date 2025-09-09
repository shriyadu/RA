package com.example.recipeapp.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.feature.favourite.FavouriteScreen
import com.example.recipeapp.feature.favourite.FavouriteVM
import com.example.recipeapp.ui.feature.onboarding.OnBoardingScreen

import com.example.recipeapp.ui.feature.dashboard.ui.DashboardScreen
import com.example.recipeapp.ui.feature.Search.ui.SearchScreen
import com.example.recipeapp.ui.feature.Search.ui.SearchViewModel
import com.example.recipeapp.ui.feature.dashboard.DashboardVM
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
import com.example.recipeapp.ui.feature.recipeDetail.RecipeDetailVM
import com.example.recipeapp.ui.recipeDetail.RecipeDetailScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigator() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnBoardingScreen(
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("dashboard") { navBackStackEntry ->
            val dashboardViewModel: DashboardVM = hiltViewModel(navBackStackEntry)

            DashboardScreen(
                viewModel = dashboardViewModel,
                onNavigateToFavourites = {
                    navController.navigate("favorite")
                },
                onNavigateToRecipeDetail = { recipeId ->
                    navController.navigate("recipeDetail/$recipeId")
                },
                onNavigateToSearch = {
                    navController.navigate("search")  // Navigate to search screen here
                }
            )
        }
        composable("favorite") {
            val favouriteViewModel = hiltViewModel<FavouriteVM>()
            FavouriteScreen(
                viewModel = favouriteViewModel,
                onRecipeClick = { recipeId ->
                    navController.navigate("recipeDetail/$recipeId")
                }
            )
        }



        composable("search") {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                viewModel = searchViewModel,
                onRecipeClick = { recipeId -> navController.navigate("recipeDetail/$recipeId") },
                onBack = { navController.popBackStack() }
            )
        }


        composable("recipeDetail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""

            val detailViewModel: RecipeDetailVM = hiltViewModel()

            RecipeDetailScreen(
                recipeId = recipeId,
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() }

            )
        }


    }

}
