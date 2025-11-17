package com.adorablehappens.gamelibrary.navigation

import android.graphics.drawable.VectorDrawable

/**
 * Реализация экранов(конечных точек) навигации
 */
sealed class RoutesScreens(
    val route: String,
//    val icon: VectorDrawable,
//    val label: String,
) : RoutesScreensFundamentals() {

    //abstract val content: KFunction<T>
//    @Composable
//    abstract fun Content(): Unit
//    @Composable
//    abstract fun Content(fd: Int): Unit

}
