package com.example.recipeapp.ui.feature.Favourite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.ui.component.RecipeListItem
import com.example.recipeapp.ui.feature.dashboard.model.Recipe

// Assuming your recipe model class is named 'Recipe' and is in this package or imported
// import com.example.recipeapp.model.Recipe // Or the correct path to your Recipe model

@Composable
fun FavoriteScreen(favoriteViewModel: FavouriteVM = viewModel()) {
    val favoriteRecipes = favoriteViewModel.favorites

    LazyColumn {
        // Explicitly specify the type of 'recipe'
        items(favoriteRecipes) { recipe: Recipe -> // Replace YourRecipeModel with the actual type
            RecipeListItem(
                recipe = recipe,
                onClick = {  }
            )
        }
    }
}
