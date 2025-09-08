package com.example.recipeapp.ui.component.ReminderBanner


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReminderTimerBanner(
    modifier: Modifier = Modifier,
    onTimeSelected: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ReminderTimerButton("30m") { onTimeSelected("30m") }
        ReminderTimerButton("1h 30m") { onTimeSelected("1h 30m") }
        ReminderTimerButton("2h") { onTimeSelected("2h") }
    }
}

@Composable
fun ReminderTimerButton(
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 22.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFFFFA76C),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}
