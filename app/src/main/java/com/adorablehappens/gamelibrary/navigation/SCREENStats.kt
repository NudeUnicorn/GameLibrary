package com.adorablehappens.gamelibrary.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.runtime.Composable

object SCREENStats  : RoutesScreens(
    route = "Stats",
    icon = Icons.Filled.CurrencyRuble,
    label = "Стоимость",
    contentDescription = "Стоимость всего",
) {
    @Composable
    override fun Content() {
        TODO("Not yet implemented")
    }

}