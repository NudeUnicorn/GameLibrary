package com.adorablehappens.gamelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.adorablehappens.gamelibrary.ui.theme.GameLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameLibraryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Column(Modifier
                        .padding(innerPadding)
                    ) {
                        Column(
                            Modifier
                                .verticalScroll(
                                    state = rememberScrollState()
                                )
                                .weight(1f)
                                .padding(bottom = 80.dp)
                        ) {
                            FrameRounded(Modifier
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

                            FrameRounded(Modifier
                                .padding(20.dp, 20.dp, 20.dp, 0.dp),
                            ) {
                                GameNewFields()
                            }

                            Box(Modifier
                                .padding(20.dp, 20.dp, 20.dp, 0.dp),
                            ){
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
                                }
                            }
                        }

                    }

                    BottomMenu()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    GameLibraryTheme {
        Greeting("Android")

        Column() {
            Column(
                Modifier
                    .verticalScroll(
                        state = rememberScrollState()
                    )
                    .weight(1f)
            ) {
                FrameRounded(Modifier.height(200.dp)) { Text("Hello!") }

                FrameRounded(Modifier) {
                    GameNewFields()
                }
            }

        }
        BottomMenu()


    }
}

@Composable
fun FrameRounded(
    modifier: Modifier = Modifier,
    backgrnd: Color = Color.LightGray,
    content: @Composable (() -> Unit) = {}
) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
            //.padding(20.dp, 20.dp, 20.dp)
            //.height(200.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(20.dp))
            .background(backgrnd)
    )
    Box(modifier = mod){
        content()
    }
}

@Composable
fun FrameRect(modifier: Modifier = Modifier, content: @Composable (() -> Unit) = {}) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
            //.padding(20.dp, 20.dp, 20.dp, 20.dp)

            //.clip(shape = RoundedCornerShape(20.dp))
            //.border(2.dp,Color.Black, shape = RoundedCornerShape(20.dp))
            .background(Color.LightGray)
            //.padding(10.dp)
    )
    Box(modifier = mod) {
        content()
    }
}

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit) = {}
) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxSize()
    )
    var dropdownMenuExpanded = remember { mutableStateOf(false) }

    Box(mod,
        Alignment.BottomCenter){
        Box(Modifier) {
            FrameRounded(
                Modifier
                    .padding(20.dp, 20.dp, 20.dp, 20.dp),
                backgrnd = Color.LightGray

            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    BottomMenuBtn(btnIcon = Icons.Filled.Home, btnDescription = "Главный экран")
                    BottomMenuBtn(btnIcon = Icons.Filled.Favorite, btnDescription = "Избранное", btnIconTint = Color.Red)
                    BottomMenuBtn(btnIcon = Icons.Filled.Shuffle, btnDescription = "Случайный список")
                    BottomMenuBtn(btnIcon = Icons.Filled.CurrencyRuble, btnDescription = "Стоимость всего")
                    BottomMenuBtn(btnIcon = Icons.Filled.Menu, btnDescription = "Меню",
                        btnOnClick = {dropdownMenuExpanded.value = true},
                        ) {BottomDropdownMenu(Modifier, expanded = dropdownMenuExpanded)}
                }
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){


                }
            }
        }
    }

}

@Composable
fun BottomMenuBtn(
    modifier: Modifier = Modifier,
    btnIcon: ImageVector = Icons.Filled.Home,
    btnIconTint: Color = Color.DarkGray,
    btnOnClick: ()-> Unit = {},
    btnDescription: String = "",
    content: @Composable (() -> Unit) = {}
) {

    val modBox: Modifier = modifier.then(
        Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(20.dp))
            //.border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))
            //.background(Color.hsl(0f, 0f, 0.7f))
            //.padding(0.dp)
    )

    Box(
        modBox
            .clickable(onClick = {
                btnOnClick()
            }),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.6f),
            imageVector = btnIcon, contentDescription = btnDescription,
            tint = btnIconTint
        )
        content()
    }

}

@Composable
fun BottomDropdownMenu(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean> = remember { mutableStateOf(false) },
    content: @Composable (() -> Unit) = {}
) {

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = {expanded.value = false}
    ) {
        DropdownMenuItem(
            text = {Text("Первый пункт")},
            onClick = {
                expanded.value = false
            }
        )
        DropdownMenuItem(
            text = {Text("Второй пункт")},
            onClick = {
                expanded.value = false
            }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = {Text("Настройки")},
            onClick = {
                expanded.value = false
            }
        )
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

        SpacerVertical1()

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
        SpacerVertical1()

        TextField(
            state = rememberTextFieldState(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("🎮 Game subtitle") },
            placeholder = { Text("Maybe game has a subtitle or slogan? Write it!") }
        )
        SpacerVertical1()

        TextField(
            state = rememberTextFieldState(initialText = stringResource(R.string.otw2_worldStory)),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("📝 World story short") },
            placeholder = { Text("Write a short world game story") }
        )
        SpacerVertical1()

        TextField(
            state = rememberTextFieldState(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("📝 Description") },
            placeholder = { Text("Just a field for short game description") }
        )
        SpacerVertical1()

        TextField(
            state = rememberTextFieldState(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("🗒️ Comment") },
            placeholder = { Text("It would be like a label") }
        )
        SpacerVertical1()

        FrameRounded(Modifier.padding(0.dp)) {
            Column(Modifier.padding(10.dp)) {
                H2Text(text = "Genres")

                Spacer(
                    modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                )

                FlowRow(Modifier.fillMaxWidth()){
                    (1..8).forEach { it ->
                        ToggleableButton(text = "genre $it")
                        SpacerHorizontal1()
                    }
                }
            }
        }
        SpacerVertical1()

        FrameRounded(Modifier.padding(0.dp)) {
            Column(Modifier.padding(10.dp)) {
                H2Text(text = "Tags")

                Spacer(
                    modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                )

                FlowRow(Modifier.fillMaxWidth()){
                    (1..8).forEach { it ->
                        ToggleableButton(text = "tag $it")
                        SpacerHorizontal1()
                    }
                }
            }
        }
        SpacerVertical1()

        FrameRounded(Modifier.padding(0.dp)) {
            Column(Modifier.padding(10.dp)) {
                H2Text(text = "Devs")

                Spacer(
                    modifier = Modifier.padding(PaddingValues(0.dp, 10.dp))
                )

                FlowRow(Modifier.fillMaxWidth()){
                    (1..8).forEach { it ->
                        ToggleableButton(text = "dev $it")
                        SpacerHorizontal1()
                    }
                }
            }
        }
        SpacerVertical1()

        TextField(
            state = rememberTextFieldState(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("️💵 Price") },
            placeholder = { Text("How much game cost?") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        SpacerVertical1()

    }
}

@Composable
fun ToggleableButton(
    modifier: Modifier = Modifier,
    text: String = "",
    backgrnd: Color = Color.LightGray,
    backgrndTggl: Color = Color.DarkGray,

) {
    var btnState by remember { mutableStateOf(false) }
    val mod: Modifier = modifier.then(
        Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(20.dp))
            .background(if (btnState) backgrndTggl else backgrnd)
            .toggleable(
                value = false,
                interactionSource = null,
                indication = LocalIndication.current,
                enabled = true,
                role = null,
                onValueChange = {value->
                    btnState = !btnState
                }
            )
            .padding(10.dp,5.dp)

    )

    Text(text = text,
        modifier = mod

    )

}

@Composable
fun H1Text(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = TextUnit(32f, TextUnitType.Sp),
    fontWeight: FontWeight = FontWeight.W900,

    ) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight
    )

}

@Composable
fun H2Text(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = TextUnit(26f, TextUnitType.Sp),
    fontWeight: FontWeight = FontWeight.W700,
    ) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight
    )

}

@Composable
fun H3Text(
    modifier: Modifier = Modifier,
    text: String = "",
    fontSize: TextUnit = TextUnit(22f, TextUnitType.Sp),
    fontWeight: FontWeight = FontWeight.W500,
) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight
    )

}

@Composable
fun SpacerVertical1(
    modifier: Modifier = Modifier,
    height: Int = 20
) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxWidth()
            .height(height.dp)
    )

    Spacer(mod)

}
@Composable
fun SpacerHorizontal1(
    modifier: Modifier = Modifier,
    width: Int = 5
) {
    val mod: Modifier = modifier.then(
        Modifier
            .fillMaxHeight()
            .width(width.dp)
    )

    Spacer(mod)

}