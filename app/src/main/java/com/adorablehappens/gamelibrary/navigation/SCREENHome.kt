package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
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
    @Composable
    override fun Content() {

        val vm: LibraryViewModel = viewModel()
        val lifecycleOwner = LocalLifecycleOwner.current
        var selectedPrimary by remember { mutableStateOf(0) }
        var selectedSecondary by remember { mutableStateOf(0) }
        val titlesPrimary = listOf("Коллекция", "Обзор", "Дополнительно")
        val titlesSecondary = listOf("Игра", "Жанры", "Тэги", "Разработчики", "Авторы", "Игровые движки")
        val pagerState = rememberPagerState(0, pageCount = { titlesPrimary.size + titlesSecondary.size })
        //val selectedDerived by remember { derivedStateOf { pagerState.currentPage*1 } }

        val games by vm.vmAllGamesLiveData.allGameEntitiesObj.observeAsState()

        Column {
            PrimaryTabRow(
                selectedTabIndex = selectedPrimary
            ) {
                titlesPrimary.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedPrimary == index,
                        onClick = { selectedPrimary = index },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                    )
                }

            }
            if (selectedPrimary == 2) {
                SecondaryScrollableTabRow(
                    selectedTabIndex = selectedSecondary
                ) {
                    titlesSecondary.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedSecondary == index,
                            onClick = { selectedSecondary = index },
                            text = {
                                Text(
                                    text = title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                        )
                    }
                }
            }

//            HorizontalPager(
//                modifier = Modifier.fillMaxSize(),
//                state = pagerState
//            ) {
//                when(pagerState.currentPage){
//                    0 -> {
//                        Test(games)
//                    }
//                    1 -> {Text("Игру к осмотру!!!")}
//                    2 -> {Text("Тэги наверное")}
//                    3 -> {Text("А вот здесь жанры или нет :)")}
//                }

//            }
        when(selectedPrimary){
            0 -> Test(games)
            1 -> {Text("Игру к осмотру!!!")}
            2 -> {}
        }
        if (selectedPrimary == 2)
            when(selectedSecondary){
                0 -> {Text("Тэги наверное")}
                1 -> {Text("А вот здесь жанры или нет :)")}
                2 -> {}
            }

        }
    }

    @Composable
    fun Test(
        games: List<GameEntity>?,
    ) {

        if (games != null) {
            Text(text = "gamesData is null")
            if (games.isEmpty()) {
                Text(text = "gamesData is empty")
            }
        }
        games?.forEach { it ->
            ListItem(
                headlineContent = { Text(text = it.name) },
                supportingContent = { Text(text = it.id.toString()) }
            )
        }
        ListItem(
            headlineContent = { Text(text = "List item baby!") },
            supportingContent = { Text(text = "Supporting") }
        )
        Card(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(150.dp, 350.dp)
                .padding(16.dp, 0.dp)
        ) {
            Text(text = "Card 1")
        }
    }


}