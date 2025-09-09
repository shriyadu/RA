package com.example.recipeapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun SearchOpenResult(
    imagePainter: Painter,
    title: String,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    onBackClick: () -> Unit,
    readyIn: String,
    servings: String,
    pricePerServing: String,
    onGetIngredientsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
    ) {
        // Banner Image with overlay, icons, title
        RecipeImage(
            imagePainter = imagePainter,
            title = title,
            isFavourite = isFavourite,
            onFavouriteClick = onFavouriteClick,

            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1.2f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row: Ready in, Servings, Price/serving
        RecipeInfoRow(
            readyIn = readyIn,
            servings = servings,
            pricePerServing = pricePerServing,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Button
        WidButton(
            text = "Get Ingredients",
            onClick = onGetIngredientsClick,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
    }
}
