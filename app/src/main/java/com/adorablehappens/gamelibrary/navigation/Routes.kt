package com.adorablehappens.gamelibrary.navigation

import androidx.compose.runtime.Composable

/**
 * Маршруты для navigation
 */
sealed class Routes(
) {
    companion object{
        val home = SCREENHome
        val favorites = SCREENFavorites
        val randomise = SCREENRandomise
        val stats = SCREENStats
        val options = SCREENOptions
        val createUpdateGame = SCREENCreateUpdateGame
        val createUpdateEX = SCREENCreateUpdateEX
    }
}