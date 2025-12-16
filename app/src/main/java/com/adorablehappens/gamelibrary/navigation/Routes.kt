package com.adorablehappens.gamelibrary.navigation

/**
 * Маршруты для navigation
 */
//sealed class Routes(
//) {
//    companion object{
//        val home = SCREENHome
//        val favorites = SCREENFavorites
//        val randomise = SCREENRandomise
//        val stats = SCREENStats
//        val options = SCREENOptions
//        val createUpdateGame = SCREENCreateUpdateGame
//        val createUpdateEX = SCREENCreateUpdateEX
//    }
//}

enum class RoutesMain(
    val route: RoutesScreens
){
    home(SCREENHome),
    favorites(SCREENFavorites),
    randomise(SCREENRandomise),
    stats(SCREENStats),
    options(SCREENOptions),
}
enum class RoutesService(
    val route: RoutesScreens
){
    createUpdateGame(SCREENCreateUpdateGame),
    createUpdateEX(SCREENCreateUpdateEX),
}