package com.example.recipeapp.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button as MaterialButton

@Composable
fun WidButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFFFF5722),
    contentColor: Color = Color.White,
    enabled: Boolean = true,
    leadingIconResId: Int? = null, // Optional drawable resource for icon
    iconTint: Color = contentColor // Icon tint defaults to button text color
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp), // Rounded edges for better style
        contentPadding = PaddingValues(vertical = 14.dp) // Taller button
    ) {
        // If icon is provided → place it before text
        if (leadingIconResId != null) {
            Icon(
                painter = painterResource(id = leadingIconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .padding(end = 8.dp),
                tint = iconTint
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
        )
    }
}

