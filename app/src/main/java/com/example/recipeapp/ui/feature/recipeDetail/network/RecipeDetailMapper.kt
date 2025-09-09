package com.example.recipeapp.ui.feature.recipeDetail.network

import com.example.recipeapp.ui.feature.recipeDetail.model.*

fun RecipeDetailResponse.toRecipeDetailModel(): RecipeDetail {
    return RecipeDetail(
        id = id.toString(),
        name = title,
        imageUrl = image,
        readyInMinutes = readyInMinutes ?: 0,
        servings = servings ?: 0,
        pricePerServing = "\u20B9${String.format("%.0f", pricePerServing ?: 0.0)}",
        ingredients = extendedIngredients.map { ingredient ->
            Ingredient(
                name = ingredient.name,
                imageUrl = "https://spoonacular.com/cdn/ingredients_100x100/${ingredient.image}"
            )
        },
        instructions = buildInstructionsString(analyzedInstructions),
        equipments = extractEquipments(analyzedInstructions),
        quickSummary = summary?.removeHtmlTags() ?: "",
        isUserFavorite = false
    )
}

fun RecipeDetail.toEntity(): RecipeDetailEntity {
    return RecipeDetailEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        readyInMinutes = readyInMinutes,
        servings = servings,
        pricePerServing = pricePerServing,
        instructions = instructions,
        quickSummary = quickSummary
    )
}

fun RecipeDetailEntity.toDomainModel(): RecipeDetail {
    return RecipeDetail(
        id = id,
        name = name,
        imageUrl = imageUrl,
        readyInMinutes = readyInMinutes,
        servings = servings,
        pricePerServing = pricePerServing,
        ingredients = emptyList(),   // Provide empty lists here, entity doesn't store these
        instructions = instructions,
        equipments = emptyList(),    // Provide empty lists here
        quickSummary = quickSummary
    )
}

// Helper functions

private fun buildInstructionsString(instructions: List<AnalyzedInstruction>?): String {
    return instructions?.flatMap { it.steps }?.joinToString("\n") { it.step } ?: ""
}

private fun extractEquipments(instructions: List<AnalyzedInstruction>?): List<Equipment> {
    val equipmentSet = mutableSetOf<Equipment>()
    instructions?.forEach { instruction ->
        instruction.steps.forEach { step ->
            step.equipment.forEach { eq ->
                equipmentSet.add(
                    Equipment(
                        name = eq.name,
                        imageUrl = "https://spoonacular.com/cdn/equipment_100x100/${eq.image}"
                    )
                )
            }
        }
    }
    return equipmentSet.toList()
}

private fun String.removeHtmlTags(): String = this.replace(Regex("<.*?>"), "")
