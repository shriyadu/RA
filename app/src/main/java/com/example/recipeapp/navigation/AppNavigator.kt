package com.example.recipeapp.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.feature.onboarding.OnBoardingScreen
import com.example.recipeapp.ui.feature.recipeDetail.RecipeDetailScreen
import com.example.recipeapp.ui.feature.recipeDetail.RecipeDetailVM
import com.example.recipeapp.ui.feature.dashboard.ui.DashboardScreen
import com.example.recipeapp.ui.feature.Favourite.FavoriteScreen
import com.example.recipeapp.ui.feature.Favourite.FavouriteVM
import com.example.recipeapp.ui.feature.dashboard.DashboardVM

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
                }
            )
        }


        composable("recipeDetail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            val detailViewModel = viewModel<RecipeDetailVM>()

            // Optionally trigger loading for recipeId here if your VM supports it
            // LaunchedEffect(recipeId) { detailViewModel.loadDummyData() }

            RecipeDetailScreen(
                recipeId = recipeId,
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() },
                onFavourite = {
                    // Dummy favorite toggle action for testing layout
                    println("Favorite toggled for recipeId: $recipeId")
                }
            )
        }
        composable("favorite") {
            val favoriteViewModel: FavouriteVM = viewModel()
            FavoriteScreen(
                favoriteViewModel = favoriteViewModel

            )
        }
    }
}
