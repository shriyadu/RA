package com.example.recipeapp.ui.feature.Search.ui



import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

import com.example.recipeapp.ui.feature.Search.data.model.RecipeSearchSuggestion
import com.example.recipeapp.ui.feature.dashboard.repository.RecipeRepository
@Composable
fun SearchScreen(
    onRecipeClick: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(Modifier.fillMaxSize().padding(10.dp)) {
        TextField(
            value = query,
            onValueChange = viewModel::onQueryChange,
            placeholder = { Text("Search Any Recipe") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(20.dp)
        )
        Spacer(Modifier.height(8.dp))
        if (isLoading) {
            CircularProgressIndicator(Modifier.padding(16.dp))
        } else {
            suggestions.forEach { suggestion ->
                Row(Modifier.fillMaxWidth().clickable { onRecipeClick(suggestion.id) }.padding(16.dp)) {
                    Text(suggestion.title)
                }
            }
        }
    }
}
