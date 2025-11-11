package com.adorablehappens.gamelibrary.navigation

sealed class Routes(
    val route: String
) {
    object Home: Routes("Home")
    object Favorites: Routes("Favorites")
    object Randomise: Routes("Randomise")
    object Stats: Routes("Stats")
    object Options: Routes("Options")
    object CreateUpdateGame: Routes("CreateUpdateGame")
}