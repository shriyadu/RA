package com.example.recipeapp.ui.feature.recipeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.model.Ingredient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.recipeapp.model.Equipments
import com.example.recipeapp.ui.component.IngredientsRow
import com.example.recipeapp.ui.component.Equipments
import com.example.recipeapp.ui.component.ExpandableSection
import com.example.recipeapp.ui.component.RecipeImage
import com.example.recipeapp.ui.component.RecipeInfoRow

@Composable
fun RecipeDetailScreen(
    recipeId: String,
    viewModel: RecipeDetailVM,
    onBack: () -> Unit,
    onFavourite: () -> Unit,
) {
    val recipeDetail by viewModel.recipeDetail.collectAsState()
    var isRecipeFavourite by remember(recipeDetail?.id) {
        mutableStateOf(recipeDetail?.isUserFavorite ?: false)
    }

    // Banner state to control the 3 different banners
    var bannerState by remember { mutableStateOf(BannerState.Hidden) }
    var reminderTime by remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        recipeDetail?.let { recipe ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Box {
                    RecipeImage(
                        modifier = Modifier.fillMaxWidth(),
                        imagePainter = rememberAsyncImagePainter(recipe.imageUrl),
                        title = recipe.name,
                        onBackClick = onBack,
                        isFavourite = isRecipeFavourite,
                        onFavouriteClick = {
                            isRecipeFavourite = !isRecipeFavourite
                            println("Favorite toggled for ${recipe.name}, new state: $isRecipeFavourite")
                            if (isRecipeFavourite) {
                                bannerState = BannerState.Favorite
                            } else {
                                bannerState = BannerState.Hidden
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                RecipeInfoRow(
                    readyIn = "${recipe.readyInMinutes} min",
                    servings = "${recipe.servings}",
                    pricePerServing = "₹156"
                )
                Spacer(modifier = Modifier.height(8.dp))
                IngredientsRow(
                    ingredients = recipe.ingredients.map { ingredient ->
                        Ingredient(
                            image = rememberAsyncImagePainter(ingredient.imageUrl),
                            name = ingredient.name,
                        )
                    },
                    modifier = Modifier.padding(8.dp),
                    title = "Ingrediants",
                )
                Spacer(modifier = Modifier.height(8.dp))
                Equipments(
                    equipments = recipe.equipments.map { equipment ->
                        Equipments(
                            image = rememberAsyncImagePainter(equipment.imageUrl),
                            name = equipment.name,
                        )
                    },
                    modifier = Modifier.padding(8.dp),
                    title = "Equipments",
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExpandableSection(
                    title = "Instructions",
                    content = "this is the expandable info section"
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExpandableSection(
                    title = "Quick Summary",
                    content = "this is the expandable info section"
                )
                Spacer(modifier = Modifier.height(100.dp)) // Add bottom padding for banner
            }
        }

        // Overlay banner at bottom
        when (bannerState) {
            BannerState.Favorite -> AddedToFavouriteBanner(
                modifier = Modifier.align(Alignment.BottomCenter),
                onAddReminder = {
                    bannerState = BannerState.Reminder
                }
            )
            BannerState.Reminder -> ReminderTimerBanner(
                modifier = Modifier.align(Alignment.BottomCenter),
                onTimeSelected = { time ->
                    reminderTime = time
                    bannerState = BannerState.Confirmation
                }
            )
            BannerState.Confirmation -> ReminderConfirmationBanner(
                modifier = Modifier.align(Alignment.BottomCenter),
                time = reminderTime,
                onOk = {
                    bannerState = BannerState.Hidden
                }
            )
            BannerState.Hidden -> {} // no banner
        }
    }
}

enum class BannerState { Favorite, Reminder, Confirmation, Hidden }

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
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .clickable(onClick = onAddReminder)
                .padding(end = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Add Reminder",
                color = Color(0xFFFFA76C),
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color(0xFFFFA76C),
                modifier = Modifier.padding(start = 4.dp).size(20.dp)
            )
        }
    }
}

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
            style = MaterialTheme.typography.titleMedium
        )
    }
}

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
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "OK",
            color = Color(0xFFFFA76C),
            modifier = Modifier
                .clickable(onClick = onOk)
                .padding(end = 18.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
