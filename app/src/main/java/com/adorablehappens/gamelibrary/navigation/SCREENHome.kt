package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel

object SCREENHome : RoutesScreens(
    route = "Home",
    icon = Icons.Filled.Home,
    label = "Главный",
    contentDescription = "Главный экран",
) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val vm: LibraryViewModel = viewModel()
        val lifecycleOwner = LocalLifecycleOwner.current
        var selectedPrimary by remember { mutableStateOf(0) }
        var selectedSecondary by remember { mutableStateOf(0) }
        val titlesPrimary = listOf("Коллекция", "Обзор", "Дополнительно")
        val titlesSecondary =
            listOf("Игра", "Жанры", "Тэги", "Разработчики", "Авторы", "Игровые движки")
        val pagerState =
            rememberPagerState(0, pageCount = { titlesPrimary.size + titlesSecondary.size })
        //val selectedDerived by remember { derivedStateOf { pagerState.currentPage*1 } }

        val games by vm.vmAllGamesLiveData.allGameEntitiesObj.observeAsState()

        val tabs = listOf<TabInfo>(
            TabInfo(
                label = "Коллекция",
                secondary = null,
                content = { Test(games) }
            ),
            TabInfo(
                label = "Обзор",
                secondary = null,
                content = {
                    Text(text = vm.vmRepo.currentGameEntityID.toString())
                }
            ),
            TabInfo(
                label = "Дополнительно",
                secondary = listOf<TabInfo>(
                    TabInfo(
                        label = "Игра",
                        secondary = null,
                        content = {RoutesService.createUpdateGame.route.Content()}
                    ),
                    TabInfo(
                        label = "Жанры",
                        secondary = null,
                        content = {ListGenres(vm = vm)}
                    ),
                    TabInfo(
                        label = "Тэги",
                        secondary = null,
                        content = {}
                    ),
                    TabInfo(
                        label = "Разработчики",
                        secondary = null,
                        content = {}
                    ),
                    TabInfo(
                        label = "Авторы",
                        secondary = null,
                        content = {}
                    ),
                    TabInfo(
                        label = "Игровые движки",
                        secondary = null,
                        content = {}
                    ),
                ),
                content = {}
            ),
        )

        Column {
            PrimaryTabRow(
                selectedTabIndex = selectedPrimary
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedPrimary == index,
                        onClick = { selectedPrimary = index },
                        text = {
                            Text(
                                text = tab.label,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                    )

                }
            }
            if (tabs[selectedPrimary].secondary != null) {
                SecondaryScrollableTabRow(
                    selectedTabIndex = selectedSecondary
                ) {
                    tabs[selectedPrimary].secondary?.forEachIndexed { index, secondaryTab ->
                        Tab(
                            selected = selectedSecondary == index,
                            onClick = { selectedSecondary = index },
                            text = {
                                Text(
                                    text = secondaryTab.label,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                        )

                    }
                }
            }

            Column (modifier = Modifier
                .fillMaxSize()
                ) {

                if (tabs[selectedPrimary].secondary == null) {
                    tabs[selectedPrimary].content()
                }
                else{

                    tabs[selectedPrimary].secondary?.get(selectedSecondary)!!.content()
                }

            }
//            ModalBottomSheet(
//                onDismissRequest = {}
//            ) {
//                Text(text = "Modal yeahhh!")
//            }

        }
    }


    @Composable
    fun Test(
        games: List<GameEntity>?,
        vm: LibraryViewModel = viewModel()
    ) {

        if (games != null){

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(games.size) { it ->
                        CardGame(game = games[it], vm = vm)
                }
            }


//                if (games.isEmpty()) {
//                    Text(text = "gamesData is empty")
//                }
//
//            games.forEach { it ->
//                ListItem(
//                    headlineContent = { Text(text = it.name) },
//                    supportingContent = { Text(text = it.id.toString()) }
//                )
//            }
//            ListItem(
//                headlineContent = { Text(text = "List item baby!") },
//                supportingContent = { Text(text = "Supporting") }
//            )
//            Card(
//                onClick = {},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .heightIn(150.dp, 350.dp)
//                    .padding(16.dp, 0.dp)
//            ) {
//                Text(text = "Card 1")
//            }
        }
    }
    @Composable
    fun CardGame(
        modifier: Modifier = Modifier,
        game: GameEntity,
        vm: LibraryViewModel
    ) {
        Card (
            onClick = {
                vm.vmRepo.currentGameEntityID = game.id
            },
            modifier = Modifier
                .aspectRatio(0.8f)
                .padding(start = 16.dp, top = 16.dp),
            ) {
            ElevatedCard (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) {

                Image(
                    Icons.Filled.Home,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
            Text(
                text = game.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(horizontal = 10.dp)
                ,
                overflow = TextOverflow.Ellipsis,
                autoSize = TextAutoSize.StepBased()
            )
        }

    }
    @Composable
    fun ListGenres(
        modifier: Modifier = Modifier,
        vm: LibraryViewModel
    ) {
        val allCurrentGameLiveData by Repository.AllGamesLiveData.allGenreEntitiesObj.observeAsState()

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            item{
                Button(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "+ Add genre")
                }
            }
            allCurrentGameLiveData?.let { it ->
                items(it.size){
                    ListItem(
                        headlineContent = {
                            Text(text = allCurrentGameLiveData!![it].name)
                        },
                        supportingContent = {
                            Text(text = allCurrentGameLiveData!![it].id.toString())
                        }
                    )
                }
            }
            item {
                FloatingActionButton(
                    onClick = {

                    }
                ) { Icon(Icons.Filled.Add, contentDescription = "Add new game to library") }
            }
        }



    }


    data class TabInfo(
        val label: String = "Tab",
        val secondary: List<TabInfo>? = null,
        val content: @Composable () -> Unit = {}
    )
}
