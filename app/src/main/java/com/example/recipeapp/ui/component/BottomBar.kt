package com.example.recipeapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar // Changed from BottomAppBar
import androidx.compose.material3.NavigationBarItem // Changed from BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(
    currentRoute: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar { // Changed from BottomNavigation
        NavigationBarItem( // Changed from BottomNavigationItem
            selected = currentRoute == "home",
            onClick = { onItemSelected("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem( // Changed from BottomNavigationItem
            selected = currentRoute == "favourite",
            onClick = { onItemSelected("favourite") },
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Favourite") },
            label = { Text("Favourite") }
        )
    }
}
