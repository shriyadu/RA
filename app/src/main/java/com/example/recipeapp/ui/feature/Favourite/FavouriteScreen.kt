package com.example.recipeapp.feature.favourite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.core.data.local.FavoriteRecipeEntity
import com.example.recipeapp.ui.component.RecipeListItem
import com.example.recipeapp.core.data.local.RecipeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    viewModel: FavouriteVM = hiltViewModel(),
    onRecipeClick: (Int) -> Unit,
) {
    val favorites = viewModel.favorites.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favourite Recipes",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (favorites.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No favourites currently")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(favorites, key = { it.id }) { fav ->
                        RecipeListItem(
                            recipe = fav.toRecipeEntity(),
                            onClick = { onRecipeClick(fav.id) }
                        )
                    }
                }
            }
        }
    }
}

fun FavoriteRecipeEntity.toRecipeEntity(): RecipeEntity = RecipeEntity(
    id = this.id,
    title = this.title,
    image = null,
    readyInMinutes = this.readyInMinutes
)