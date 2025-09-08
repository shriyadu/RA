package com.example.recipeapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.ui.feature.dashboard.model.Recipe

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp) // Maintain fixed height for consistency
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) { // Use Box to layer elements
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxSize() // Image takes the full size of the Box/Card
                    .clip(RoundedCornerShape(12.dp)), // Clip the image to the card's shape
                contentScale = ContentScale.Crop
            )

            // Gradient overlay for better text readability (optional but recommended)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.1f), // Slightly transparent at the top of text
                                Color.Black.copy(alpha = 0.7f)  // More opaque at the bottom where text is
                            ),
                            startY = 300f // Adjust startY to control where gradient starts from top
                        )
                    )
            )

            // Column for Text elements, aligned to the bottom of the Box
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart) // Align text to the bottom-left
                    .fillMaxWidth()
                    .padding(8.dp) // Padding for the text content
            ) {
                Text(
                    text = recipe.title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp)) // Small space between title and time
                Text(
                    text = "Ready in ${recipe.readyInMinutes} min",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp), // Smaller font for subtitle
                    color = Color.White.copy(alpha = 0.9f) // Slightly less prominent white
                )
            }
        }
    }
}
