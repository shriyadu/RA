package com.example.recipeapp.ui.feature.recipeDetail.model

data class RecipeDetailResponse(
    val id: Int,
    val title: String,
    val image: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val pricePerServing: Double?,
    val extendedIngredients: List<IngredientResponse>,
    val analyzedInstructions: List<AnalyzedInstruction>?,
    val summary: String?
)

data class IngredientResponse(
    val name: String,
    val image: String?
)

data class AnalyzedInstruction(
    val name: String?,
    val steps: List<InstructionStep>
)

data class InstructionStep(
    val number: Int,
    val step: String,
    val equipment: List<EquipmentResponse>
)

data class EquipmentResponse(
    val name: String,
    val image: String?
)
