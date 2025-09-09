package com.example.recipeapp.ui.recipeDetail

import IngredientsRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.core.data.local.FavoriteRecipeEntity
import com.example.recipeapp.ui.component.*
import com.example.recipeapp.ui.component.ReminderBanner.AddedToFavouriteBanner
import com.example.recipeapp.ui.component.ReminderBanner.ReminderConfirmationBanner
import com.example.recipeapp.ui.component.ReminderBanner.ReminderTimerBanner
import com.example.recipeapp.ui.feature.recipeDetail.RecipeDetailVM
import kotlinx.coroutines.launch

enum class BannerState {
    Favorite,
    Reminder,
    Confirmation,
    Hidden
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: String,
    viewModel: RecipeDetailVM = hiltViewModel(),
    onBack: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val recipeDetail by viewModel.recipeDetail.collectAsState()

    // Load recipe detail on recipeId change
    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetail(recipeId)
    }

    var isRecipeFavourite by remember(recipeDetail?.id) { mutableStateOf(false) }

    // Check favorite status from DB whenever recipeDetail.id changes
    LaunchedEffect(recipeDetail?.id) {
        recipeDetail?.id?.let { id ->
            coroutineScope.launch {
                isRecipeFavourite = viewModel.isFavorite(id.toInt())
            }
        }
    }

    var bannerState by remember { mutableStateOf(BannerState.Hidden) }
    var reminderTime by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = recipeDetail?.name ?: "Recipe Detail",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                if (recipeDetail == null) {
                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                } else {
                    recipeDetail?.let { recipe ->
                        RecipeImage(
                            modifier = Modifier.fillMaxWidth(),
                            imagePainter = rememberAsyncImagePainter(recipe.imageUrl),
                            title = recipe.name,
                            isFavourite = isRecipeFavourite,
                            onFavouriteClick = {
                                coroutineScope.launch {
                                    val newState = !isRecipeFavourite
                                    isRecipeFavourite = newState
                                    val fav = FavoriteRecipeEntity(
                                        id = recipe.id.toInt(),
                                        title = recipe.name,
                                        readyInMinutes = recipe.readyInMinutes
                                    )
                                    if (newState) {
                                        viewModel.addFavorite(fav)
                                        bannerState = BannerState.Favorite
                                    } else {
                                        viewModel.removeFavorite(fav)
                                        bannerState = BannerState.Hidden
                                    }
                                }
                            }
                        )

                        Spacer(Modifier.height(8.dp))

                        RecipeInfoRow(
                            readyIn = "${recipe.readyInMinutes} min",
                            servings = "${recipe.servings}",
                            pricePerServing = recipe.pricePerServing
                        )

                        IngredientsRow(
                            ingredients = recipe.ingredients,
                            modifier = Modifier.padding(8.dp),
                            title = "Ingredients"
                        )

                        Equipments(
                            equipments = recipe.equipments,
                            modifier = Modifier.padding(8.dp),
                            title = "Equipment"
                        )

                        ExpandableSection(
                            title = "Instructions",
                            content = recipe.instructions
                        )

                        ExpandableSection(
                            title = "Quick Summary",
                            content = recipe.quickSummary
                        )
                    }
                }
            }

            // Overlay banners pinned at bottom with padding
            when (bannerState) {
                BannerState.Favorite -> AddedToFavouriteBanner(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    onAddReminder = { bannerState = BannerState.Reminder }
                )
                BannerState.Reminder -> ReminderTimerBanner(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    onTimeSelected = {
                        reminderTime = it
                        bannerState = BannerState.Confirmation
                    }
                )
                BannerState.Confirmation -> ReminderConfirmationBanner(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    time = reminderTime,
                    onOk = { bannerState = BannerState.Hidden }
                )
                BannerState.Hidden -> { /* no banner visible */ }
            }
        }
    }
}
