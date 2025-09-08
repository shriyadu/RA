package com.example.recipeapp.ui.feature.dashboard.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.ui.component.RecipeSearchBar
import com.example.recipeapp.ui.component.RecipeCard
import com.example.recipeapp.ui.component.RecipeListItem
import com.example.recipeapp.ui.feature.dashboard.DashboardVM


@Composable
fun DashboardScreen(

    onNavigateToFavourites: () -> Unit,
    onNavigateToRecipeDetail: (int: Int) -> Unit,

    viewModel: DashboardVM = hiltViewModel(),
) {

    val popularRecipes by viewModel.popularRecipes.collectAsState()
    val allRecipes by viewModel.allRecipes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Already on Home */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToFavourites,
                    icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Favourite") },
                    label = { Text("Favourite") }
                )
            }
        }
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Header and search bar as a single item
                item {
                    Text(
                        text = "👋 Hey ",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Discover tasty and healthy recipes",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalContentColor.current.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    var searchText by remember { mutableStateOf("") }

                    RecipeSearchBar(
                        value = searchText,
                        onValueChange = { searchText = it }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Popular Recipes",
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),

                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(popularRecipes, key = { it.id }) { recipe ->
                            RecipeCard(recipe = recipe, onClick = { onNavigateToRecipeDetail(recipe.id) })
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "All Recipes",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(allRecipes, key = { it.id }) { recipe ->
                    RecipeListItem(recipe = recipe, onClick = { onNavigateToRecipeDetail(recipe.id) })
                }
            }
        }
    }
}
