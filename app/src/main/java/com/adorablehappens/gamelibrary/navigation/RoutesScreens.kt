package com.adorablehappens.gamelibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Реализация экранов(конечных точек) навигации
 */
sealed class RoutesScreens(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val contentDescription: String,
//    val icon: VectorDrawable,
//    val label: String,
) : RoutesScreensFundamentals() {

    //abstract val content: KFunction<T>
//    @Composable
//    abstract fun Content(): Unit
//    @Composable
//    abstract fun Content(fd: Int): Unit

    @Composable
    open fun Content() {}

}
