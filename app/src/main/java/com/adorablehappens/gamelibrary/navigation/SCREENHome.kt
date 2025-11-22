package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
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
        var showCreateUpdateDialog by remember { mutableStateOf(false) }
        var contentCreateUpdateDialog by remember { mutableStateOf(@Composable {}) }

        val games by vm.vmAllGamesLiveData.allGameEntitiesObj.observeAsState()
        val allGenreEntities by Repository.AllGamesLiveData.allGenreEntitiesObj.observeAsState()

        val tabGenres = @Composable {
            ListOfEntities(
                vm = vm,
                addBtnOnClick = {
                    contentCreateUpdateDialog = {GenreCreateUpdate(
                        onDismissRequest = {showCreateUpdateDialog = false},
                        onConfirmation = { name, description, comment, entity ->
                            Repository.behGenreRepo.addNew(
                                GenreEntity(
                                    id = 0,
                                    name = name,
                                    description = description,
                                    comment = comment
                                )
                            )
                        }
                    )}
                    showCreateUpdateDialog = true
                },
                modBtnOnClick = {id ->
                    contentCreateUpdateDialog = {
                        GenreCreateUpdate(
                            onDismissRequest = { showCreateUpdateDialog = false },
                            onConfirmation = {name, description, comment, entity ->
                                if (entity != null){
                                    Repository.behGenreRepo.update(
                                        GenreEntity(
                                            id = entity.id,
                                            name = name,
                                            description = description,
                                            comment = comment
                                        )
                                    )
                                }
                            },
                            entity = allGenreEntities?.let { it ->
                                it.filter { it.id == id }[0]
                            }
                        )
                    }
                    showCreateUpdateDialog = true
                },
                delBtnOnClick = {id ->
                    contentCreateUpdateDialog = {
                        DeleteConfirmation(
                            onDismissRequest = {showCreateUpdateDialog = false},
                            onConfirmation = {
                                val entity = allGenreEntities?.let { it ->
                                    it.filter { it.id == id }[0]}

                                entity?.let {
                                    Repository.behGenreRepo.deleteOne(entity = entity)
                                }
                                showCreateUpdateDialog = false
                            }
                        )
                    }
                    showCreateUpdateDialog = true
                },
                allEntitiesData = allGenreEntities
            )
        }

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
                        content = {tabGenres()}
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                if (tabs[selectedPrimary].secondary == null) {
                    tabs[selectedPrimary].content()
                } else {

                    tabs[selectedPrimary].secondary?.get(selectedSecondary)!!.content()
                }

            }
//            ModalBottomSheet(
//                onDismissRequest = {}
//            ) {
//                Text(text = "Modal yeahhh!")
//            }

            if (showCreateUpdateDialog){
                DialogCreateUpdate(
                    onDismissRequest = {showCreateUpdateDialog = false},
                    onConfirmation = {showCreateUpdateDialog = false},
                    content = {contentCreateUpdateDialog()}
                )
            }
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
                item {
                    CardAddGame(vm = vm)
                }
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
    fun CardAddGame(
        modifier: Modifier = Modifier,
        vm: LibraryViewModel? = null
    ) {
        Card (
            onClick = {

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
                    Icons.Filled.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
            Text(
                text = "Add game",
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
    fun ListOfEntities(
        modifier: Modifier = Modifier,
        vm: LibraryViewModel,
        allEntitiesData: List<EntityBase>?,
        addBtnOnClick: ()->Unit = {},
        modBtnOnClick: (entityID: Long)->Unit = {},
        delBtnOnClick: (entityID: Long)->Unit = {},
    ) {

        Column {  }
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            item{
                Button(
                    onClick = {
                        addBtnOnClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "+ Add genre")
                }
            }
            allEntitiesData?.let { it ->
                items(it.size){
                    ListItem(
                        headlineContent = {
                            Text(text = allEntitiesData[it].name)
                        },
                        overlineContent = {
                            Text(text = "ID " + allEntitiesData[it].id.toString())
                        },
                        supportingContent = {
                            Text(text = allEntitiesData[it].description)
                        },
                        trailingContent = {
                            Row {

                                IconButton(
                                    onClick = {
                                        modBtnOnClick(allEntitiesData[it].id)
                                    }
                                ) {
                                    Icon(Icons.Filled.AddCircle,"")
                                }
                                IconButton(
                                    onClick = {
                                        delBtnOnClick(allEntitiesData[it].id)
                                    }
                                ) {
                                    Icon(Icons.Filled.RemoveCircle,"")
                                }
                            }
                        }
                    )
                }
            }
        }



    }

    @Composable
    fun DialogCreateUpdate(
        onDismissRequest: () -> Unit = {},
        onConfirmation: () -> Unit = {},
        content: @Composable () -> Unit = {}
    ) {

        Dialog(onDismissRequest = { onDismissRequest() }) {
            // Draw a rectangle shape with rounded corners inside the dialog

            content()
        }

    }
    @Composable
    fun GenreCreateUpdate(
        entity: GenreEntity? = null,
        onDismissRequest: () -> Unit = {},
        onConfirmation: (
                name: String,
                description: String,
                comment: String,
                entity: GenreEntity?
                ) -> Unit,
    ) {
        val name = rememberTextFieldState(entity?.name ?: "")
        val description = rememberTextFieldState(entity?.description ?: "")
        val comment = rememberTextFieldState(entity?.comment ?: "")


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Create or update existing genre",
                    modifier = Modifier.padding(16.dp),
                )
                TextField(
                    state = name,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("📝 Genre title") },
                    placeholder = { Text("How genre named?") },

                )
                TextField(
                    state = description,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("📝 Description") },
                    placeholder = { Text("Describe genre") }
                )
                TextField(
                    state = comment,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("📝 Comment") },
                    placeholder = { Text("Write a comment") }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    TextButton(
                        onClick = {
                            onConfirmation(
                                name.text.toString(),
                                description.text.toString(),
                                comment.text.toString(),
                                entity
                            )
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
    @Composable
    fun DeleteConfirmation(
        onConfirmation: () -> Unit = {},
        onDismissRequest: () -> Unit = {},
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Are you sure?",
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "You really want to delete?",
                    modifier = Modifier.padding(16.dp),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    TextButton(
                        onClick = {
                            onConfirmation()
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Delete")
                    }
                }
            }
        }

    }


        data class TabInfo(
        val label: String = "Tab",
        val secondary: List<TabInfo>? = null,
        val content: @Composable () -> Unit = {}
    )
}
