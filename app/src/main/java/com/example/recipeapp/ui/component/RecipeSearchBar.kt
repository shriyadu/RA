package com.example.recipeapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
// import androidx.compose.material3.SegmentedButtonDefaults.Icon // This import seems unused and can be removed
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults // Keep this import
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RecipeSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search Any Recipe"
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth(),

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color(0xFF1A1A1A)
            )
        },
        placeholder = {
            Text(
                text = placeholder,

                color = Color(0xFFC2B6B6)
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors( // Changed from textFieldColors to colors
            focusedContainerColor = Color(0xFFF5F7FA), // Renamed containerColor to focusedContainerColor
            unfocusedContainerColor = Color(0xFFF5F7FA), // Added unfocusedContainerColor for consistency
            disabledContainerColor = Color(0xFFF5F7FA), // Added disabledContainerColor for consistency
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}
