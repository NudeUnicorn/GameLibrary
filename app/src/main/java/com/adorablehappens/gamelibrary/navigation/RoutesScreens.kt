package com.adorablehappens.gamelibrary.navigation

import androidx.compose.runtime.Composable

/**
 * Реализация экранов(конечных точек) навигации
 */
sealed class RoutesScreens(
    val route: String,
): RoutesScreensFundamentals() {

    @Composable
    abstract fun Content(): Unit

    companion object{
        val home: RoutesScreens = SCREENHome
        val favorites: RoutesScreens = SCREENFavorites
        val randomise: RoutesScreens = SCREENRandomise
        val stats: RoutesScreens = SCREENStats
        val options: RoutesScreens = SCREENOptions
        val createUpdateGame: RoutesScreens = SCREENCreateUpdateGame
    }
}

/**
 * Основные элементы граф. интерфейса
 */
open class RoutesScreensFundamentals(){

    fun fd(){

    }

}