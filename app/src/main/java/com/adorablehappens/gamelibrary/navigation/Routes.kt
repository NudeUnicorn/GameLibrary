package com.adorablehappens.gamelibrary.navigation

import androidx.compose.runtime.Composable

/**
 * Маршруты для navigation
 */
sealed class Routes(
    val route: String,
    val destination: @Composable ()->Unit
) {
    object Home: Routes(
        RoutesScreens.home.route,
        { RoutesScreens.home.Content() }
    )
    object Favorites: Routes(
        RoutesScreens.favorites.route,
        { RoutesScreens.favorites.Content() }
        )
    object Randomise: Routes(
        RoutesScreens.randomise.route,
        { RoutesScreens.randomise.Content() }
    )
    object Stats: Routes(
        RoutesScreens.stats.route,
        { RoutesScreens.stats.Content() }
    )
    object Options: Routes(
        RoutesScreens.options.route,
        { RoutesScreens.options.Content() }
    )
    object CreateUpdateGame: Routes(
        RoutesScreens.createUpdateGame.route,
        { RoutesScreens.createUpdateGame.Content() }
    )
}