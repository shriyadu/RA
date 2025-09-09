package com.example.recipeapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite // For filled heart
import androidx.compose.material.icons.filled.FavoriteBorder // For outlined heart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton // Using IconButton for better semantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // For property delegation
import androidx.compose.runtime.mutableStateOf // For local state
import androidx.compose.runtime.remember // For local state
import androidx.compose.runtime.setValue // For property delegation
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecipeImage(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    title: String,
     // Added for back navigation
    isFavourite: Boolean,        // State should be hoisted from the caller
    onFavouriteClick: () -> Unit // Callback to notify the caller of the change
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f / 1.2f) // Adjust aspect ratio as needed
    ) {
        Image(
            painter = imagePainter,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Gradient overlay for text readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Top bar for back button and favorite icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),

            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {




            IconButton(
                onClick = onFavouriteClick, // Use the passed lambda
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                    .size(40.dp) // Consistent size
            ) {
                Icon(
                    imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (isFavourite) "Unmark as Favourite" else "Mark as Favourite",
                    // When it's a favorite, tint it red. Otherwise, use white for the border.
                    tint = if (isFavourite) Color.Red else Color.White
                )
            }
        }

        // Title at the bottom
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp // Slightly larger title
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}
