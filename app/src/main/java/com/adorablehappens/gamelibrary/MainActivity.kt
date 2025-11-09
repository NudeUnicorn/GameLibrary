package com.adorablehappens.gamelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
                    Column () {
                        Box (Modifier.weight(1f)) {

                            Column() {

                                FrameRounded(Modifier.height(200.dp)){Text("Hello!")}

                                FrameRounded(){
                                    GameNewFields()

                                }
                            }
                        }

                        FrameRounded(Modifier.height(80.dp)){Text("Hello!")}
                    }
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
//        Column () {
//            FrameRounded(Modifier.height(200.dp)){Text("Hello!")}
//            FrameRounded(Modifier.weight(1f)){
//                GameNewFields()
//            }
//            FrameRounded(Modifier.height(80.dp)){Text("Hello!")}
//        }

        Column () {
            Box (Modifier.weight(1f)) {

                Column {

                    FrameRounded(Modifier.height(200.dp)){Text("Hello!")}

                    FrameRounded(){
                        GameNewFields()

                    }
                }
            }

            FrameRounded(Modifier.height(80.dp)){Text("Hello!")}
        }


    }
}

@Composable
fun FrameRounded(modifier: Modifier = Modifier, content: @Composable (() -> Unit) = {}) {
    val mod: Modifier = modifier.then(
        Modifier.fillMaxWidth()
            .padding(20.dp,20.dp,20.dp)
            //.height(200.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .border(2.dp,Color.Black, shape = RoundedCornerShape(20.dp))
            .background(Color.LightGray)
    )
    Box(modifier = mod){
        content()
    }
}

@Composable
fun FrameRect(modifier: Modifier = Modifier, content: @Composable (() -> Unit) = {}) {
    val mod: Modifier = modifier.then(
        Modifier.fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 20.dp)

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
fun GameNewFields(modifier: Modifier = Modifier) {
    val mod: Modifier = modifier.then(
        Modifier.fillMaxWidth()
            .padding(10.dp)
    )
    val textFieldColors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent
    )
    LazyColumn(mod) {
        item {
            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = {Text("🎮 Game title")},
                placeholder = {Text("What is the title of a game?")},
                //supportingText = {Text("Supporting text")}
                colors = textFieldColors
            )
            Spacer(
                modifier = Modifier.padding(PaddingValues(0.dp,10.dp))
            )
        }
        item {
            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = {Text("🎮 Game subtitle")},
                placeholder = {Text("Maybe game has a subtitle or slogan? Write it!")}
            )
            Spacer(
                modifier = Modifier.padding(PaddingValues(0.dp,10.dp))
            )
        }
        item {
            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = {Text("📝 World story short")},
                placeholder = {Text("Write a short world game story")}
            )
            Spacer(
                modifier = Modifier.padding(PaddingValues(0.dp,10.dp))
            )
        }
        item {
            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = {Text("📝 Description")},
                placeholder = {Text("Just a field for short game description")}
            )
            Spacer(
                modifier = Modifier.padding(PaddingValues(0.dp,10.dp))
            )
        }
        item {
            TextField(
                state = rememberTextFieldState(),
                modifier = Modifier.fillMaxWidth(),
                label = {Text("🗒️ Comment")},
                placeholder = {Text("It would be like a label")}
            )
            Spacer(
                modifier = Modifier.padding(PaddingValues(0.dp,10.dp))
            )
        }
    }
}
