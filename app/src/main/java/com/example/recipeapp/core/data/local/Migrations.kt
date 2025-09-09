package com.example.recipeapp.core.data.local



import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create new table for RecipeDetailEntity (adjust columns as per your entity)
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS recipe_details (
                id TEXT NOT NULL PRIMARY KEY,
                name TEXT NOT NULL,
                imageUrl TEXT,
                readyInMinutes INTEGER NOT NULL,
                servings INTEGER NOT NULL,
                pricePerServing TEXT NOT NULL,
                instructions TEXT NOT NULL,
                quickSummary TEXT NOT NULL
            )
        """.trimIndent())
    }
}
