package com.example.recipeapp.ui.component.ReminderBanner



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddedToFavouriteBanner(
    modifier: Modifier = Modifier,
    onAddReminder: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = Color(0xFF1A1A1A),
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Added to Favourite",
            color = Color.White,
            modifier = Modifier
                .padding(start = 18.dp)
                .weight(1f),
            fontSize = 17.sp
        )
        Box(
            Modifier
                .clickable(onClick = onAddReminder)
                .padding(end = 18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Add Reminder",
                    color = Color(0xFFFFA76C),
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color(0xFFFFA76C),
                    modifier = Modifier.size(18.dp).padding(start = 2.dp)
                )
            }
        }
    }
}
