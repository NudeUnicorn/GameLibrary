package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel

object SCREENFavorites  : RoutesScreens(
    route = "Favorites",
    icon = Icons.Filled.Favorite,
    label = "Избранное",
    contentDescription = "Список избранного",
) {
    @Composable
    override fun Content() {
        val vm: LibraryViewModel = viewModel()
        Text(text = vm.vmTest.value.let { return@let it + 1 }.toString())
        Button(
            onClick = {vm.vmTest.value++},
            Modifier.fillMaxWidth().height(45.dp)
        )
        {
            Text(text = "vm +1")
        }
    }

}