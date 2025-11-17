package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow

object SCREENHome  : RoutesScreens(
    route = "Home",
) {
    @Composable
    fun Content() {

        var state by remember { mutableStateOf(0) }
        val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")

        Column {
            PrimaryTabRow(
                selectedTabIndex = state
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = {state=index},
                        text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                    )
                }

            }
        }
    }

}