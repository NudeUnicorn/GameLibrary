package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.services.GamesTimeManager
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel

object SCREENStats  : RoutesScreens(
    route = "Stats",
    icon = Icons.Filled.CurrencyRuble,
    label = "Стоимость",
    contentDescription = "Стоимость всего",
) {
    @Composable
    override fun Content() {
        val vm: AppOverallViewModel = viewModel()
        val games by vm.vmAllGamesLiveData.allGameEntitiesObj.observeAsState()

        var priceAll by remember { mutableIntStateOf(0) }
        var playingTimeAll by remember { mutableLongStateOf(0.toLong()) }
        priceAll = priceAll(games)
        playingTimeAll = playtimeAll(games)
        val playingTimeAllStr = GamesTimeManager.getFormatTimeOverall(playingTimeAll)

        Column {

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Labels(
                    Modifier.weight(0.33f),
                    stringResource(R.string.stats_gamescount),
                    games?.size.toString())
                Labels(
                    Modifier.weight(0.33f),
                    stringResource(R.string.stats_games_price),
                    priceAll.toString())
                Labels(
                    Modifier.weight(0.33f),
                    stringResource(R.string.stats_overallplayed),
                    playingTimeAllStr)
            }
            ListGames(games)
        }
    }

    @Composable
    fun ListGames(
        entities: List<GameEntity>?
    ){
        val height = 14
        entities?.let {
            LazyColumn {
                items(entities.size){index ->
                    ListItem(
                        headlineContent = {
                            Text(text = entities[index].name)
                        },
                        supportingContent = {
                            Row(
                                //Modifier.height(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.AccessTime,
                                    stringResource(R.string.stats_gamelist_entity_overallplayed),
                                    Modifier.height(height.dp)
                                )
                                Box(Modifier.padding(end = 5.dp))
                                Text(
                                    text = if (entities[index].overallPlaying == 0.toLong()) "---" else GamesTimeManager.getFormatTimeOverall(entities[index].overallPlaying),
                                    lineHeight = TextUnit(height.toFloat(), TextUnitType.Sp)
                                )
                                Box(Modifier.padding(end = 5.dp))
                                Icon(Icons.Filled.CurrencyRuble,
                                    stringResource(R.string.stats_gamelist_entity_price),
                                    Modifier.height(height.dp)
                                    )
                                Box(Modifier.padding(end = 5.dp))
                                Text(
                                    text = entities[index].price.toString(),
                                    lineHeight = TextUnit(height.toFloat(), TextUnitType.Sp)
                                )
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun Labels(
        modifier: Modifier,
        label: String,
        text: String
    ){
        val mod = modifier.then(Modifier)
        Column(mod) {
            Text(
                text = label,
                fontSize = TextUnit(10f, TextUnitType.Sp),
                lineHeight = TextUnit(10f, TextUnitType.Sp),
            )
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    fun priceAll(
        entities: List<GameEntity>?
    ): Int {
        var priceAll = 0
        entities?.let { games ->
            games.forEach { priceAll += it.price }
        }
        return priceAll
    }
    fun playtimeAll(
        entities: List<GameEntity>?
    ): Long {
        var playtimeAll = 0.toLong()
        entities?.let { games ->
            games.forEach { playtimeAll += it.overallPlaying }
        }
        return playtimeAll
    }

}