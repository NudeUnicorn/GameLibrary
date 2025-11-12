package com.adorablehappens.gamelibrary.navigation

/**
 * Реализация экранов(конечных точек) навигации
 */
sealed class RoutesScreens(
    val route: String,
) : RoutesScreensFundamentals() {

    //abstract val content: KFunction<T>
//    @Composable
//    abstract fun Content(): Unit
//    @Composable
//    abstract fun Content(fd: Int): Unit

}
