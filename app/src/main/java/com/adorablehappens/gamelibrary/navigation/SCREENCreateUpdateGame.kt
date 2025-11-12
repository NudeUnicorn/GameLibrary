package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.FrameRounded
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.H1Text
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.H2Text
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.SpacerVerticalFill
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.ToggleableButton

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

    @Composable
    fun GameNewFields(
        modifier: Modifier = Modifier
    ) {
        val mod: Modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        val textFieldColors: TextFieldColors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
        Column(mod) {
            H1Text(text = "About the game")

            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(
                    initialText = stringResource(R.string.otw2_title)
                ),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🎮 Game title") },
                placeholder = { Text("What is the title of a game?") },
                //supportingText = {Text("Supporting text")}
                colors = textFieldColors
            )
            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🎮 Game subtitle") },
                placeholder = { Text("Maybe game has a subtitle or slogan? Write it!") }
            )
            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(initialText = stringResource(R.string.otw2_worldStory)),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("📝 World story short") },
                placeholder = { Text("Write a short world game story") }
            )
            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("📝 Description") },
                placeholder = { Text("Just a field for short game description") }
            )
            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🗒️ Comment") },
                placeholder = { Text("It would be like a label") }
            )
            SpacerVerticalFill()

            FrameRounded(Modifier.padding(0.dp)) {
                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Genres")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth()) {
                        (1..8).forEach { it ->
                            ToggleableButton(
                                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 5.dp),
                                text = "genre $it"
                            )
                        }
                    }
                }
            }
            SpacerVerticalFill()

            FrameRounded(Modifier.padding(0.dp)) {
                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Tags")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth()) {
                        (1..8).forEach { it ->
                            ToggleableButton(
                                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 5.dp),
                                text = "tag $it"
                            )
                        }
                    }
                }
            }
            SpacerVerticalFill()

            FrameRounded(Modifier.padding(0.dp)) {
                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Devs")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth()) {
                        (1..8).forEach { it ->
                            ToggleableButton(
                                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 5.dp),
                                text = "dev $it"
                            )
                        }
                    }
                }
            }
            SpacerVerticalFill()

            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("️💵 Price") },
                placeholder = { Text("How much game cost?") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            SpacerVerticalFill()

        }
    }

}