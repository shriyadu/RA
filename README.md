# RecipeApp
An offline-first, pixel-perfect Android app built with Kotlin, Jetpack Compose, and MVVM + UDF + EDA architecture. Explore delicious recipes, view detailed ingredients, instructions, nutrition, and mark your favorites to revisit anytime—even offline.

✨ Features
Home Screen

Fetch and display popular recipes using Get Random Recipes API.

Explore all available recipes using Search Recipes API.

Ads appear every 5 recipes for monetization.

Recipe Details

Open any recipe to view:

Ingredients

Full Cooking Instructions

Nutritional Info

Similar Recipes

Smooth bottom-sheet navigation with stack-based transitions:

Ingredients → Full Recipe → Similar Recipes

Previous states visible, swipe down to return seamlessly.

Search & Favourites

Search recipes with filtering & sorting.

Mark recipes as favorite, persisted locally in DB.

Cached favorites available offline.

Offline-First Architecture

Room Database for caching both recipe lists and details.

On app start → Show cached recipes instantly, then refresh in background.

Favourites sync with the DB, enabling offline access.

Pixel-Perfect UI

Crafted with Jetpack Compose, optimized for accessibility and responsiveness.

Interactive bottom-sheets with smooth transition animations.

🏗️ Tech Stack
Language → Kotlin

Architecture → MVVM + Unidirectional Data Flow (UDF) + Event-Driven Architecture (EDA)

UI → Jetpack Compose + Material 3

Data Persistence → Room DB (Cache-First strategy)

Networking → Retrofit + OkHttp

Coroutines & Flow → Asynchronous background work + reactive UI updates

Dependency Injection → Dagger/Hilt

Ad Integration → Native Ads (inject every 5th recipe)

🔄 Data Flow
API Layer → Fetches data from Random Recipes / Search Recipes endpoints.

Repository Layer → Manages data, applying cache-first strategy with Room.

ViewModel → Exposes UI state using StateFlow/LiveData with UDF principles.

UI (Compose) → Consumes state, renders recipes, and reacts to events.

📦 Project Modules
core-network → API calls (Retrofit/OkHttp).

core-database → Room entities, DAO, migrations.

feature-home → Popular/All Recipes listing with ads.

feature-recipe-detail → Multi-layer bottom-sheet with ingredients, instructions, nutrients, similar recipes.

feature-favourites → Persistent list of user-marked favourite recipes.

ui-components → Design system, Compose UI components.


Minimum SDK: 24

Gradle Plugin: 8.x
