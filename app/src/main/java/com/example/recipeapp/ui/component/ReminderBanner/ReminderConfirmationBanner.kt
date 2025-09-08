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
fun ReminderConfirmationBanner(
    modifier: Modifier = Modifier,
    time: String,
    onOk: () -> Unit
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
            text = "You will be reminded in $time",
            color = Color.White,
            modifier = Modifier
                .padding(start = 18.dp)
                .weight(1f),
            fontSize = 17.sp
        )
        Text(
            text = "OK",
            color = Color(0xFFFFA76C),
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            modifier = Modifier
                .clickable(onClick = onOk)
                .padding(end = 18.dp)
        )
    }
}
