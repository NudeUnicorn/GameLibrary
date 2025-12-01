package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
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

        var priceAll by remember { mutableStateOf(0) }
        priceAll = priceAll(games)

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Labels(
                Modifier.weight(0.5f),
                "Всего игр",
                games?.size.toString())
            Labels(
                Modifier.weight(0.5f),
                "Стоимость всего",
                priceAll.toString())
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

}