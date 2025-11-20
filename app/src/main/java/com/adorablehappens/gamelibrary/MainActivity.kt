package com.adorablehappens.gamelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adorablehappens.gamelibrary.navigation.RoutesMain
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.BottomMenu
import com.adorablehappens.gamelibrary.navigation.RoutesService
import com.adorablehappens.gamelibrary.ui.theme.GameLibraryTheme
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val coroutineMainActivity = MainScope()

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
            val coroutineScope = rememberCoroutineScope()
            val navController = rememberNavController()
            var navStartDestination by remember { mutableStateOf(RoutesService.createUpdateGame.route.route) }
            val vm: LibraryViewModel = viewModel()
            vm.setNavController(navController)

            GameLibraryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {},
                    bottomBar = {
                        BottomMenu(navController = navController)
                    },
                    floatingActionButton = {
//                        if (navStartDestination == RoutesMain.home.route.route)
//                        FloatingActionButton(
//                            onClick = {
//                                navController.navigate(RoutesService.createUpdateGame.route.route)
//                            }
//                        ) { Icon(Icons.Filled.Add, contentDescription = "Add new game to library") }
                    }
                ) { innerPadding ->


                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    NavHost(navController,
                        RoutesService.createUpdateGame.route.route,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        composable(RoutesMain.home.route.route) {
                            navStartDestination = RoutesMain.home.route.route
                            RoutesMain.home.route.Content()
                        }
                        composable(RoutesMain.favorites.route.route) {
                            navStartDestination = RoutesMain.favorites.route.route
                        }
                        composable(RoutesMain.randomise.route.route) {
                            navStartDestination = RoutesMain.randomise.route.route
                        }
                        composable(RoutesMain.stats.route.route) {
                            navStartDestination = RoutesMain.stats.route.route
                        }
                        composable(RoutesMain.options.route.route) {
                            navStartDestination = RoutesMain.options.route.route
                            RoutesMain.options.route.Content()
                        }
                        composable(RoutesService.createUpdateGame.route.route) {
                            navStartDestination = RoutesService.createUpdateGame.route.route
                            RoutesService.createUpdateGame.route.Content()
                        }
                        composable(RoutesService.createUpdateEX.route.route) {
                            navStartDestination = RoutesService.createUpdateEX.route.route
                            RoutesService.createUpdateEX.route.Content()
                        }
                    }


                    //BottomMenu()

                }
            }
        }
    }

    fun execCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        coroutine: suspend ()-> Unit
    ){
        coroutineMainActivity.launch { coroutine() }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineMainActivity.cancel()
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

        //Routes.createUpdateGame.Content()
        BottomMenu(navController = rememberNavController())


    }
}
