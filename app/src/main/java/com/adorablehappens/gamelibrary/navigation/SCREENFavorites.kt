package com.adorablehappens.gamelibrary.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable

object SCREENFavorites  : RoutesScreens(
    route = "Favorites",
    icon = Icons.Filled.Favorite,
    label = "Избранное",
    contentDescription = "Список избранного",
) {
    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

}