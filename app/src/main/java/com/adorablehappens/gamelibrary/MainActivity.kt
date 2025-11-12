package com.adorablehappens.gamelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adorablehappens.gamelibrary.navigation.Routes
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.Companion.BottomMenu
import com.adorablehappens.gamelibrary.ui.theme.GameLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.auto(
//                lightScrim = Color.Black.copy(alpha = 0.1f).toArgb(),
//                darkScrim = Color.White.copy(alpha = 0.1f).toArgb()
//            ),
//            navigationBarStyle = SystemBarStyle.auto(
//                Color.Transparent.toArgb(),
//                Color.Transparent.toArgb(),
//            )
        )
        setContent {
            val navController = rememberNavController()

            GameLibraryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(
                            Modifier,
                            //.background(Color.Blue)
                            //.padding(20.dp, 0.dp, 20.dp, 0.dp)
                        ) {
                            BottomMenu(navController = navController)
                        }
                    }
                ) { innerPadding ->


                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    NavHost(navController, Routes.createUpdateGame.route) {
                        composable(Routes.home.route) {
                            Routes.home
                        }
                        composable(Routes.favorites.route) {
                        }
                        composable(Routes.randomise.route) {
                        }
                        composable(Routes.stats.route) {
                        }
                        composable(Routes.options.route) {
                        }
                        composable(Routes.createUpdateGame.route) {
                            Routes.createUpdateGame.Content()
                        }
                    }


                    //BottomMenu()

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

        Routes.createUpdateGame.Content { }
        BottomMenu(navController = rememberNavController())


    }
}
