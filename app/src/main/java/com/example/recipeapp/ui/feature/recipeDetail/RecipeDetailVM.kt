package com.example.recipeapp.ui.feature.recipeDetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Ingredient(val name: String, val imageUrl: String?)
data class Equipment(val name: String, val imageUrl: String?)
data class Nutrition(val summary: String, val bad: String, val good: String)
data class RecipeDetail(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val readyInMinutes: Int,
    val servings: Int,
    val pricePerServing: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val equipments: List<Equipment>,
    val quickSummary: String,
    var isUserFavorite: Boolean = false,
    val nutrition: Nutrition
)

class RecipeDetailVM : ViewModel() {
    private val _recipeDetail = MutableStateFlow<RecipeDetail?>(null)
    val recipeDetail: StateFlow<RecipeDetail?> = _recipeDetail.asStateFlow()

    init { loadDummyData() }

    private fun loadDummyData() {
        // Static dummy data for preview
        _recipeDetail.value = RecipeDetail(
            id = "1",
            name = "Shahi Paneer",
            imageUrl = "https://images.example.com/shahi-paneer.jpg",
            readyInMinutes = 25,
            servings = 6,
            pricePerServing = "₹156",
            ingredients = listOf(
                Ingredient("Onion", "https://images.example.com/onion.jpg"),
                Ingredient("Paneer", "https://images.example.com/paneer.jpg"),
                Ingredient("Butter", "https://images.example.com/butter.jpg"),
                Ingredient("Ginger", "https://images.example.com/ginger.jpg")
            ),
            instructions = "Lorem ipsum dolor sit amet consectetur. Sagittis facilisis aliquet aenean lorem ullamcorper et. ...",
            equipments = listOf(
                Equipment("Equipment name", null),
                Equipment("Equipment name", null)
            ),
            quickSummary = "Lorem ipsum dolor sit amet consectetur. Sagittis facilisis aliquet aenean lorem ullamcorper et. ...",
            nutrition = Nutrition(
                summary = "Lorem ipsum dolor sit amet consectetur...",
                bad = "Bad for health nutrition",
                good = "Good for health nutrition"
            )
        )
    }
}
