package com.adorablehappens.gamelibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Реализация экранов(конечных точек) навигации
 */
sealed class RoutesScreens() : RoutesScreensFundamentals() {
    abstract val route: String
    abstract val icon: ImageVector
    abstract val label: String
    abstract val contentDescription: String

    //abstract val content: KFunction<T>
//    @Composable
//    abstract fun Content(): Unit
//    @Composable
//    abstract fun Content(fd: Int): Unit

    @Composable
    open fun Content() {}

}
