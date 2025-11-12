package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

object SCREENCreateUpdateGame : RoutesScreens(
    route = "CreateUpdateGame",
) {

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        val mod: Modifier = modifier.then(
            Modifier
                .fillMaxSize()
        )
        val createOrUpdate = remember { mutableStateOf(false) }

        Column(
            Modifier
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .verticalScroll(
                        state = rememberScrollState()
                    )
                    .weight(1f)
                    .padding(bottom = 0.dp)
            ) {
                FrameRounded(
                    Modifier
                        .height(200.dp)
                        .padding(20.dp, 20.dp, 20.dp, 0.dp)
                ) {
                    Text("Hello!")
                    Image(
                        imageVector = Icons.Filled.Games,
                        contentDescription = "Select game splash image by clicking",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = {

                            })
                    )
                }

                FrameRounded(
                    Modifier
                        .padding(20.dp, 20.dp, 20.dp, 0.dp),
                ) {
                    GameNewFields()
                }

                Box(
                    Modifier
                        .padding(20.dp, 20.dp, 20.dp, 0.dp),
                ) {
                    Column {

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                            //.height(40.dp)
                            ,
                            onClick = {

                            }) {
                            Text(text = "Save game")
                        }

                        SpacerVerticalFill()
                    }
                }

                content()
            }

        }

    }
}