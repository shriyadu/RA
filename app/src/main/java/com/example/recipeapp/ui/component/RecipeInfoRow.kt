package com.example.recipeapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope // Import RowScope if you need to pass it explicitly
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RecipeInfoRow(
    readyIn: String,
    servings: String,
    pricePerServing: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color(0xFFB3E5FC)), // Light blue border
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Apply weight modifier here, on the call to InfoBox
        InfoBox(
            modifier = Modifier.weight(1f), // Apply weight here
            label = "Ready in",
            value = readyIn,
            valueColor = Color(0xFFFF5722)
        )
        InfoBox(
            modifier = Modifier.weight(1f), // Apply weight here
            label = "Servings",
            value = servings,
            valueColor = Color(0xFF3A3A3A)
        )
        InfoBox(
            modifier = Modifier.weight(1f), // Apply weight here
            label = "Price/serving",
            value = pricePerServing,
            valueColor = Color(0xFFFF5722)
        )
    }
}

@Composable
fun InfoBox(
    label: String,
    value: String,
    valueColor: Color,
    modifier: Modifier = Modifier // Accept a Modifier
) {
    Column(
        // Use the modifier passed in, which now includes the weight from the parent RowScope
        modifier = modifier
            .padding(vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = valueColor
        )
    }
}
