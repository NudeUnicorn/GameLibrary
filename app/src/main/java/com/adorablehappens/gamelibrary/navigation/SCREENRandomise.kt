package com.adorablehappens.gamelibrary.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.Composable

object SCREENRandomise  : RoutesScreens(
    route = "Randomise",
    icon = Icons.Filled.Shuffle,
    label = "Случайное",
    contentDescription = "Случайный список",
) {
    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

}