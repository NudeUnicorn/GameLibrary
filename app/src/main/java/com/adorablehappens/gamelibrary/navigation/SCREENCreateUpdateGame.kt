package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Games
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.H1Text
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.H2Text
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.SpacerVerticalFill
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel
import java.util.Calendar

object SCREENCreateUpdateGame : RoutesScreens(
    route = "CreateUpdateGame",
    icon = Icons.Filled.Create,
    label = "",
    contentDescription = "",
) {

    @Composable
    override fun Content() {
        val vm: LibraryViewModel = viewModel()
        val mod: Modifier =
            Modifier
                .fillMaxSize()
        val createOrUpdate = remember { mutableStateOf(false) }

        val name = rememberTextFieldState(stringResource(R.string.otw2_title))
        val subname = rememberTextFieldState("")
        val worldStoryShort = rememberTextFieldState(initialText = stringResource(R.string.otw2_worldStory))
        val description = rememberTextFieldState("Here will be a description")
        val comment = rememberTextFieldState("No comment for now")
        val price = rememberTextFieldState("0")

        Column(
            Modifier
                .padding(0.dp)
        ) {
            Column(
                Modifier
                    .verticalScroll(
                        state = rememberScrollState()
                    )
                    .weight(1f)
                    .padding(bottom = 0.dp)
            ) {
                Card(
                    onClick = {},
                    Modifier
                        .height(200.dp)
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                ) {
                    Text("Hello!")
                    Image(
                        imageVector = Icons.Filled.Games,
                        contentDescription = "Select game splash image by clicking",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Card (
                    Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp),
                ) {
                    GameNewFields(
                        name = name,
                        subname = subname,
                        worldStoryShort = worldStoryShort,
                        description = description,
                        comment = comment,
                        price = price
                    )
                }

                Box(
                    Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp),
                ) {
                    Column {

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                            //.height(40.dp)
                            ,
                            onClick = {
                                vm.vmRepo.behGameRepo.addNew(GameEntity(
                                    id = 0,
                                    name = name.text.toString(),
                                    subname = subname.text.toString(),
                                    wordStoryShort = worldStoryShort.text.toString(),
                                    image = "",
                                    imageIcon = "",
                                    favorite = false,
                                    price = price.text.toString().toInt(),
                                    startPlaying = 0,
                                    stopPlaying = 0,
                                    overallPlaying = 0,
                                    description = description.text.toString(),
                                    comment = comment.text.toString(),
                                    timestamp = Calendar.getInstance().timeInMillis
                                ))
                            }) {
                            Text(text = "Save game")
                        }

                        SpacerVerticalFill()
                    }
                }

            }

        }

    }

    @Composable
    fun GameNewFields(
        modifier: Modifier = Modifier,
        name: TextFieldState = rememberTextFieldState(),
        subname: TextFieldState = rememberTextFieldState(),
        worldStoryShort: TextFieldState = rememberTextFieldState(),
        description: TextFieldState = rememberTextFieldState(),
        comment: TextFieldState = rememberTextFieldState(),
        price: TextFieldState = rememberTextFieldState(),
    ) {
        val mod: Modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        val cardColors = CardColors(
            containerColor = Color.White,
            contentColor = CardDefaults.cardColors().contentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
        )
        val textFieldColors: TextFieldColors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
        val selected = mutableListOf<Boolean>(true,false,false,true,false,true,false,false)
        val selectedState by remember { mutableStateOf(selected.toMutableStateList()) }
        Column(mod) {
            H1Text(text = "About the game")

            SpacerVerticalFill()

            TextField(
                state = name,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🎮 Game title") },
                placeholder = { Text("What is the title of a game?") },
                //supportingText = {Text("Supporting text")}
                colors = textFieldColors
            )
            SpacerVerticalFill()

            TextField(
                state = subname,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🎮 Game subtitle") },
                placeholder = { Text("Maybe game has a subtitle or slogan? Write it!") }
            )
            SpacerVerticalFill()

            TextField(
                state = worldStoryShort,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("📝 World story short") },
                placeholder = { Text("Write a short world game story") }
            )
            SpacerVerticalFill()

            TextField(
                state = description,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("📝 Description") },
                placeholder = { Text("Just a field for short game description") }
            )
            SpacerVerticalFill()

            TextField(
                state = comment,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("🗒️ Comment") },
                placeholder = { Text("It would be like a label") }
            )
            SpacerVerticalFill()

            ElevatedCard (Modifier.padding(0.dp),
                ) {
                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Genres")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        selectedState.forEachIndexed { index, it ->
                            FilterChip(
                                selected = it,
                                onClick = { selectedState[index] = !selectedState[index] },
                                label = { Text(text = "Chip $index") },
                                leadingIcon = {
                                    if (it)
                                        Icon(Icons.Filled.Done, contentDescription = "")
                                }
                            )
                            Spacer(Modifier.width(5.dp))
                        }
                    }
                }

                HorizontalDivider()

                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Tags")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                        selectedState.forEachIndexed { index,it ->
                            FilterChip(
                                selected = it,
                                onClick = {selectedState[index] = !selectedState[index]},
                                label = {Text(text = "Chip $index")},
                                leadingIcon = {
                                    if (it)
                                    Icon(Icons.Filled.Done, contentDescription = "")
                                }
                            )
                            Spacer(Modifier.width(5.dp))
                        }
                    }
                }

                HorizontalDivider()

                Column(Modifier.padding(10.dp)) {
                    H2Text(text = "Devs")

                    Spacer(
                        modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                    )

                    FlowRow(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        selectedState.forEachIndexed { index, it ->
                            FilterChip(
                                selected = it,
                                onClick = { selectedState[index] = !selectedState[index] },
                                label = { Text(text = "Chip $index") },
                                leadingIcon = {
                                    if (it)
                                        Icon(Icons.Filled.Done, contentDescription = "")
                                }
                            )
                            Spacer(Modifier.width(5.dp))
                        }
                    }
                }
            }
            SpacerVerticalFill()

            TextField(
                state = price,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("️💵 Price") },
                placeholder = { Text("How much game cost?") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            SpacerVerticalFill()

        }
    }

}