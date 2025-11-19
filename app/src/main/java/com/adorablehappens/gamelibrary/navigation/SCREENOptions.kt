package com.adorablehappens.gamelibrary.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

object SCREENOptions  : RoutesScreens(
    route = "Options",
    icon = Icons.Filled.Menu,
    label = "Меню",
    contentDescription = "Меню",
) {
    @Composable
    override fun Content() {
        SCREENCreateUpdateGame.Content()
    }

}