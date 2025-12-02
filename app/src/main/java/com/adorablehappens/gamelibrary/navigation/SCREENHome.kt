package com.adorablehappens.gamelibrary.navigation

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.createBitmap
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.SpacerVerticalFill
import com.adorablehappens.gamelibrary.navigation.SCREENCreateUpdateGame.GameNewFields
import com.adorablehappens.gamelibrary.services.GameTimeType
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel
import java.util.Calendar

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
        var selectedPrimary by remember { vm.selectedPrimary }
        var selectedSecondary by remember { vm.selectedSecondary }

        val showCreateUpdateDialog = remember { vm.showCreateUpdateDialog }
        val contentCreateUpdateDialog = remember { vm.contentCreateUpdateDialog }

        val games by vm.vmAllLiveData.allGameEntitiesObj.observeAsState()
        val allGenreEntities by vm.vmAllLiveData.allGenreEntitiesObj.observeAsState()




        val tabs = listOf<TabInfo>(
            TabInfo(
                label = "Коллекция",
                secondary = null,
                content = {
                    LibraryGrid(
                        games,
                        vm,
                        contentCreateUpdateDialog,
                        showCreateUpdateDialog
                    )
                }
            ),
            TabInfo(
                label = "Обзор",
                secondary = null,
                content = {
                    TabGameOverview(
                        vm = vm,
                        contentCreateUpdateDialog = contentCreateUpdateDialog,
                        showCreateUpdateDialog = showCreateUpdateDialog
                    )
//                    Text(text = vm.vmRepo.currentGameEntityID.toString())
                    println("Current game ID - " + vm.vmRepo.currentGameEntityID.toString())
//                    Text(text = vm.vmTest.value.let { return@let it + 1 }.toString())

                }
            ),
            TabInfo(
                label = "Дополнительно",
                secondary = listOf<TabInfo>(
                    TabInfo(
                        label = "Игры",
                        secondary = null,
                        content = {TabGames(
                            vm = vm,
                            allEntities = games,
                            showCreateUpdateDialog = showCreateUpdateDialog,
                            contentCreateUpdateDialog = contentCreateUpdateDialog
                        )}
                    ),
                    TabInfo(
                        label = "Жанры",
                        secondary = null,
                        content = {TabGenres(
                            vm = vm,
                            allGenreEntities = allGenreEntities,
                            showCreateUpdateDialog = showCreateUpdateDialog,
                            contentCreateUpdateDialog = contentCreateUpdateDialog
                        )}
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
                                maxLines = 1,
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
                                    maxLines = 1,
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

            if (showCreateUpdateDialog.value){
                DialogHolder(
                    onDismissRequest = {showCreateUpdateDialog.value = false},
                    onConfirmation = {showCreateUpdateDialog.value = false},
                    content = {contentCreateUpdateDialog.value()}
                )
            }
        }
    }

    @Composable
    fun TabGameOverview(
        vm: LibraryViewModel,
        showCreateUpdateDialog: MutableState<Boolean>,
        contentCreateUpdateDialog:  MutableState<@Composable ()-> Unit>,
    )
    {
        GameOverview(
            vm = vm,
            addCheatBtnOnClick = {},
            addWalkBtnOnClick = {},
            startStopBtnOnClick = {},
            modBtnOnClick = {},
            delBtnOnClick = {},
        )
    }

    @Composable
    fun GameOverview(
        modifier: Modifier = Modifier,
        vm: LibraryViewModel,
        addCheatBtnOnClick: ()->Unit = {},
        addWalkBtnOnClick: ()->Unit = {},
        startStopBtnOnClick: ()->Unit = {},
        modBtnOnClick: (entityID: Long)->Unit = {},
        delBtnOnClick: (entityID: Long)->Unit = {},
        whatToAddText: String = "+ Add genre"
    )
    {
        val currentGame = vm.vmAllCurrentLiveData.currentGameObj.observeAsState()
        println("Current game in overview - " + currentGame.value?.name)
        val coroutine = rememberCoroutineScope()

        val added by vm.vmRepo.gamesTimeManager.stateInitial.collectAsState()
        val startPlaying by vm.vmRepo.gamesTimeManager.stateStartPlaying.collectAsState()
        val stopPlaying by vm.vmRepo.gamesTimeManager.stateStopPlaying.collectAsState()
        val overallPlaying by vm.vmRepo.gamesTimeManager.stateOverallPlayed.collectAsState()


        if (currentGame.value == null){

        }
        else{
            vm.vmRepo.gamesTimeManager.getFormatTimeS(currentGame.value!!.timestamp, GameTimeType.Init)
            vm.vmRepo.gamesTimeManager.getFormatTimeS(currentGame.value!!.startPlaying, GameTimeType.Start)
            vm.vmRepo.gamesTimeManager.getFormatTimeS(currentGame.value!!.stopPlaying, GameTimeType.Stop)
            vm.vmRepo.gamesTimeManager.getFormatTimeS(currentGame.value!!.overallPlaying, GameTimeType.Overall)

            var image: Bitmap? by remember {
                vm.vmRepo.imageCacher.imageGet(currentGame.value?.id ?: 0, currentGame.value?.image)?.let {
                    vm.vmImage.value = it
                    vm.vmImage
                } ?: vm.vmImage.apply { value = createBitmap(1, 1) }
                //vm.vmImage
            }

            Column(
                Modifier
                    .padding(0.dp)
            )
            {
                Column(
                    Modifier
                        .verticalScroll(
                            state = rememberScrollState()
                        )
                        .weight(1f)
                        .padding(bottom = 0.dp)
                )
                {
                    Card(
                        Modifier
                            .height(200.dp)
                            .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    )
                    {

                        Image(
                            bitmap = image?.asImageBitmap() ?: createBitmap(1, 1).asImageBitmap(),
                            contentDescription = "Game main image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(128.dp)
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(0.35f),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {

                            GameTimeStatsInfo(
                                label = "добавлено",
                                time = added ?: ""
                            )

                            HorizontalDivider(Modifier.padding(vertical = 10.dp))

                            GameTimeStatsInfo(
                                label = "впервые сыграно",
                                time = startPlaying ?: ""
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.3f),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .fillMaxSize(),
                                onClick = {}
                            ) {
                                Icon(
                                    Icons.Filled.PlayCircle,
                                    "",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.weight(0.35f)
                        ) {

                            GameTimeStatsInfo(
                                label = "всего сыграно",
                                time = overallPlaying ?: ""
                            )

                            HorizontalDivider(Modifier.padding(vertical = 10.dp))

                            GameTimeStatsInfo(
                                label = "окончание игры",
                                time = stopPlaying ?: ""
                            )
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun GameTimeStatsInfo(
        label: String = "",
        time: String? = ""
    ){
        Text(
            text = label,
            fontSize = TextUnit(9f, TextUnitType.Sp),
            lineHeight = TextUnit(9f, TextUnitType.Sp),
        )
        Text(
            text = time ?: "",
            fontSize = TextUnit(11f, TextUnitType.Sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
            //lineHeight = TextUnit(12f, TextUnitType.Sp),
        )
    }

    @Composable
    fun TabGenres(
        vm: LibraryViewModel,
        allGenreEntities: List<GenreEntity>?,
        showCreateUpdateDialog: MutableState<Boolean>,
        contentCreateUpdateDialog:  MutableState<@Composable ()-> Unit>,
    ){
//        var contentCreateUpdateDialog = contentCreateUpdateDialog1
//        var showCreateUpdateDialog = showCreateUpdateDialog1
        ListOfEntities(
            vm = vm,
            whatToAddText = "+ Add genre",
            addBtnOnClick = {
                contentCreateUpdateDialog.value = {
                    GenreCreateUpdate(
                    onDismissRequest = {showCreateUpdateDialog.value = false},
                    onConfirmation = { name, description, comment, entity ->
                        Repository.behGenreRepo.addNew(
                            GenreEntity(
                                id = 0,
                                name = name,
                                description = description,
                                comment = comment
                            )
                        )
                        showCreateUpdateDialog.value = false
                    }
                )}
                showCreateUpdateDialog.value = true
            },
            modBtnOnClick = {id ->
                contentCreateUpdateDialog.value = {
                    GenreCreateUpdate(
                        onDismissRequest = { showCreateUpdateDialog.value = false },
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
                            showCreateUpdateDialog.value = false
                        },
                        entity = allGenreEntities?.let { it ->
                            it.filter { it.id == id }[0]
                        }
                    )
                }
                showCreateUpdateDialog.value = true
            },
            delBtnOnClick = {id ->
                val entity = allGenreEntities?.let { it ->
                    it.filter { it.id == id }[0]}

                entity?.let{

                    contentCreateUpdateDialog.value = {
                        DeleteConfirmation(
                            onDismissRequest = {showCreateUpdateDialog.value = false},
                            onConfirmation = {

                                Repository.behGenreRepo.deleteOne(entity = entity)

                                showCreateUpdateDialog.value = false
                            }
                        )
                    }
                    showCreateUpdateDialog.value = true
                }
            },
            allEntitiesData = allGenreEntities
        )
    }
    @Composable
    fun TabGames(
        vm: LibraryViewModel,
        allEntities: List<GameEntity>?,
        showCreateUpdateDialog: MutableState<Boolean>,
        contentCreateUpdateDialog:  MutableState<@Composable ()-> Unit>,
    ){

        ListOfEntities(
            vm = vm,
            whatToAddText = "+ Add game",
            addBtnOnClick = {
                contentCreateUpdateDialog.value = {
                    GameCreateUpdate(
                        onDismissRequest = { showCreateUpdateDialog.value = false },
                        onConfirmation = {name, subname, worldStoryShort, imageFilename, price, description, comment, entity, uri, favorite ->
                            val imageFilename = vm.vmRepo.imageManager.saveImageToInternalStorage(uri) ?: ""
                            vm.vmRepo.behGameRepo.addNew(GameEntity(
                                id = 0,
                                name = name,
                                subname = subname,
                                wordStoryShort = worldStoryShort,
                                image = imageFilename,
                                imageIcon = "",
                                favorite = favorite,
                                price = price.toInt(),
                                startPlaying = 0,
                                stopPlaying = 0,
                                overallPlaying = 0,
                                description = description,
                                comment = comment,
                                timestamp = Calendar.getInstance().timeInMillis
                            ))
                            showCreateUpdateDialog.value = false
                        },
                        vm = vm,
                    )}
                showCreateUpdateDialog.value = true
            },
            modBtnOnClick = {id ->
                contentCreateUpdateDialog.value = {
                    GameCreateUpdate(
                        onDismissRequest = { showCreateUpdateDialog.value = false },
                        onConfirmation = {name, subname, worldStoryShort, imageFilename, price, description, comment, entity, uri, favorite ->
                            entity?.let{
                                var imageFilename1: String = ""
                                if (entity.image != imageFilename) {
                                    vm.vmRepo.imageManager.deleteImage(entity.image)
                                    imageFilename1 =
                                        vm.vmRepo.imageManager.saveImageToInternalStorage(uri) ?: ""
                                } else {
                                    imageFilename1 = imageFilename
                                }
                                vm.vmRepo.behGameRepo.update(
                                    GameEntity(
                                        id = entity.id,
                                        name = name,
                                        subname = subname,
                                        wordStoryShort = worldStoryShort,
                                        image = imageFilename1,
                                        imageIcon = entity.imageIcon,
                                        favorite = favorite,
                                        price = price,
                                        startPlaying = entity.startPlaying,
                                        stopPlaying = entity.stopPlaying,
                                        overallPlaying = entity.overallPlaying,
                                        description = description,
                                        comment = comment,
                                        timestamp = entity.timestamp
                                    )
                                )
                            }
                            showCreateUpdateDialog.value = false
                        },
                        entity = allEntities?.let { it ->
                            it.filter { it.id == id }[0]
                        },
                        vm = vm
                    )
                }
                showCreateUpdateDialog.value = true
            },
            delBtnOnClick = {id ->
                val entity = allEntities?.let { it ->
                    it.filter { it.id == id }[0]}

                entity?.let{
                    contentCreateUpdateDialog.value = {
                        DeleteConfirmation(
                            onDismissRequest = {showCreateUpdateDialog.value = false},
                            onConfirmation = {
                                    vm.vmRepo.behGameRepo.deleteOne(entity = entity)

                                showCreateUpdateDialog.value = false
                            },
                            description = "ID ${entity.id} - ${entity.name}"
                        )
                    }
                    showCreateUpdateDialog.value = true
                }

            },
            allEntitiesData = allEntities
        )
    }


    @Composable
    fun LibraryGrid(
        games: List<GameEntity>?,
        vm: LibraryViewModel,
        contentCreateUpdateDialog: MutableState<@Composable ()-> Unit>,
        showCreateUpdateDialog: MutableState<Boolean>
    ) {

        games?.let {gameEntities ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    start = 0.dp,16.dp,16.dp,16.dp)
            ) {
                item {
                    CardAddGame(
                        vm = vm,
                        contentCreateUpdateDialog = contentCreateUpdateDialog,
                        showCreateUpdateDialog = showCreateUpdateDialog
                        )
                }
                items(games.size) { it ->
                    CardGame(entity = games[it], vm = vm)
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
        entity: GameEntity,
        vm: LibraryViewModel
    ) {
        val image: Bitmap? by remember { mutableStateOf(vm.vmRepo.imageCacher.imageGet(entity.id, entity.image)) }

        Card (
            onClick = {
                vm.vmRepo.currentGameEntityID = entity.id
                vm.selectedPrimary.value = 1
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
                    bitmap = image?.asImageBitmap() ?: createBitmap(1,1).asImageBitmap(),
                    contentDescription = entity.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
            Box (
                modifier= Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                ,
                contentAlignment = Alignment.Center
            ){

                Text(
                    text = entity.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                    ,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    textAlign = TextAlign.Center,
                    lineHeight = TextUnit(10f, TextUnitType.Sp),
                    overflow = TextOverflow.Ellipsis,
                    //autoSize = TextAutoSize.StepBased()
                )
            }
        }

    }
    @Composable
    fun CardAddGame(
        modifier: Modifier = Modifier,
        vm: LibraryViewModel,
        contentCreateUpdateDialog: MutableState<@Composable ()-> Unit>,
        showCreateUpdateDialog: MutableState<Boolean>
    ) {
        Card (
            onClick = {
                contentCreateUpdateDialog.value = {
                    GameCreateUpdate(
                        onDismissRequest = { showCreateUpdateDialog.value = false },
                        onConfirmation = {name, subname, worldStoryShort, imageFilename, price, description, comment, entity, uri, favorite ->
                            val imageFilename = vm.vmRepo.imageManager.saveImageToInternalStorage(uri) ?: ""
                            vm.vmRepo.behGameRepo.addNew(GameEntity(
                                id = 0,
                                name = name,
                                subname = subname,
                                wordStoryShort = worldStoryShort,
                                image = imageFilename,
                                imageIcon = "",
                                favorite = favorite,
                                price = price.toInt(),
                                startPlaying = 0,
                                stopPlaying = 0,
                                overallPlaying = 0,
                                description = description,
                                comment = comment,
                                timestamp = Calendar.getInstance().timeInMillis
                            ))
                            showCreateUpdateDialog.value = false
                        },
                        vm = vm,
                    )}
                showCreateUpdateDialog.value = true
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
            Box (
                modifier= Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add game",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    textAlign = TextAlign.Center,
                    lineHeight = TextUnit(10f, TextUnitType.Sp),
                )
            }
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
        whatToAddText: String = "+ Add genre"
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn (
                modifier = Modifier.weight(1f)
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
                        Text(text = whatToAddText)
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
            Row(
                modifier= Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Элементов - " + allEntitiesData?.size,
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                    lineHeight = TextUnit(12f, TextUnitType.Sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }



    }

    @Composable
    fun DialogHolder(
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
    )
    {
        val name = rememberTextFieldState(entity?.name ?: "")
        val description = rememberTextFieldState(entity?.description ?: "")
        val comment = rememberTextFieldState(entity?.comment ?: "")


        Card(
            modifier = Modifier
                .fillMaxWidth()
                //.height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    //.fillMaxSize()
                ,
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
    fun GameCreateUpdate(
        vm: LibraryViewModel,
        entity: GameEntity? = null,
        onDismissRequest: () -> Unit = {},
        onConfirmation: (
            name: String,
            subname: String,
            worldStoryShort: String,
            imageFilename: String,
            price: Int,
            description: String,
            comment: String,
            entity: GameEntity?,
            uri: Uri?,
            favorite: Boolean,
        ) -> Unit,
    )
    {

        val name = rememberTextFieldState(entity?.name ?: "")
        val description = rememberTextFieldState(entity?.description ?: "")
        val comment = rememberTextFieldState(entity?.comment ?: "")

        val mod: Modifier =
            Modifier
                .fillMaxSize()

        val subname = rememberTextFieldState(entity?.subname ?: "")
        val worldStoryShort = rememberTextFieldState(initialText = entity?.wordStoryShort ?: stringResource(R.string.otw2_worldStory))
        var imageFilename by remember { mutableStateOf(entity?.image ?: "") }
        val favorite = rememberSaveable {
            entity?.let {
                vm.vmFavorite.value = it.favorite
                vm.vmFavorite
            } ?: vm.vmFavorite
        }
        println("Favorite - " + favorite.value)
        val price = rememberTextFieldState(entity?.price?.toString() ?: "0")

        val uri = remember { mutableStateOf(Uri.EMPTY) }

        var image: Bitmap? by remember {
            vm.vmRepo.imageCacher.imageGet(entity?.id ?: 0, imageFilename)?.let {
                vm.vmImage.value = it
                vm.vmImage
            }?: vm.vmImage.apply { value = createBitmap(1,1) }
            //vm.vmImage
        }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uriResult ->
                if (uriResult !== null){
                    uri.value = uriResult
                    image = vm.vmRepo.imageManager.previewImage(uri.value)
                    imageFilename = vm.vmRepo.imageManager.getNewImageFilename()
                }
            }
        )


        Card(
            modifier = Modifier
                .fillMaxWidth()
                //.height(375.dp)
                //.padding(16.dp)
            ,
            //shape = RoundedCornerShape(16.dp),
        )
        {
            Column(
                modifier = Modifier
                    //.fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {

                Column(
                    Modifier
                        .verticalScroll(
                            state = rememberScrollState()
                        )
                        .weight(1f)
                        .padding(bottom = 0.dp)
                )
                {
                    Card(
                        onClick = {
                            launcher.launch("image/*")
                            println("URI is - $uri")
                        },
                        Modifier
                            .height(200.dp)
                            .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    ) {

                        Image(
                            bitmap = image?.asImageBitmap() ?: createBitmap(1,1).asImageBitmap(),
                            contentDescription = "Select game splash image by clicking",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                    }

                    GameNewFields(
                        name = name,
                        subname = subname,
                        worldStoryShort = worldStoryShort,
                        favorite = favorite,
                        description = description,
                        comment = comment,
                        price = price
                    )

                    Box(
                        Modifier
                            .padding(16.dp, 16.dp, 16.dp, 0.dp),
                    ) {
                        Column {

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                //.height(40.dp)
                                ,
                                onClick = {
                                    onConfirmation(
                                        name.text.toString(),
                                        subname.text.toString(),
                                        worldStoryShort.text.toString(),
                                        imageFilename,
                                        price.text.toString().toIntOrNull() ?: 0,
                                        description.text.toString(),
                                        comment.text.toString(),
                                        entity,
                                        uri.value,
                                        favorite.value
                                    )
                                }) {
                                Text(text = "Save game")
                            }

                            SpacerVerticalFill()
                        }
                    }

                }

            }
        }
    }
    @Composable
    fun DeleteConfirmation(
        onConfirmation: () -> Unit = {},
        onDismissRequest: () -> Unit = {},
        description: String? = null
    ) {
        val descr = description ?: ""

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    //.fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Are you sure?",
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "You really want to delete? \n $descr",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
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
