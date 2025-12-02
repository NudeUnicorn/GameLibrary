package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel

object SCREENRandomise  : RoutesScreens(
    route = "Randomise",
    icon = Icons.Filled.Shuffle,
    label = "Случайное",
    contentDescription = "Случайный список",
) {
    @Composable
    override fun Content() {
        val vm: AppOverallViewModel = viewModel()
        val games by vm.vmAllGamesLiveData.allGameEntitiesObj.observeAsState()

        Box(
            contentAlignment = Alignment.Center
        ) {
            RandomUI(
                vm = vm,
                entities = games
            )

        }
    }

    @Composable
    fun RandomUI(
        vm: AppOverallViewModel,
        entities: List<GameEntity>?
    ){
        var entity: GameEntity
        entities?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                if (vm.vmRepo.appOptions.canChooseRandomGame.collectAsState().value){
                    IconButton(
                        onClick = {
                            entity = entities.random()
                            println("Random game is - " + entity.name)

                            val title = vm.vmRepo.appOptions.randomGameTitle
                            vm.vmRepo.appOptions.randomGameTitle.value = entity.name
                            vm.vmRepo.appOptions.randomGameTitle =
                                vm.vmRepo.appOptions.randomGameTitle
                            //vm.vmRepo.appOptions.setChooseRandomTime()
                        },
                        modifier = Modifier.size(128.dp)
                    ) {
                        Icon(Icons.Filled.Shuffle,
                            "Click to get a random game",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = "Click on button and will \nluck smile to you!",
                        textAlign = TextAlign.Center
                    )
                }
                else{
                    Text(
                        text = "Congrats! Your game for today is",
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = vm.vmRepo.appOptions.randomGameTitle.value ?: "",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}