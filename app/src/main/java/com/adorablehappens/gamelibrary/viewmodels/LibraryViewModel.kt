package com.adorablehappens.gamelibrary.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.createBitmap
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity

class LibraryViewModel(): ViewModel() {

    val vmRepo = Repository
    val vmAllLiveData = Repository.AllLiveData
    val vmAllCurrentLiveData
        get() = Repository.Data.getAllCurrentGameData()

    val vmName: MutableState<String> = mutableStateOf("")
    val vmDescription: MutableState<String> = mutableStateOf("")
    val vmComment: MutableState<String> = mutableStateOf("")
    val vmSubname: MutableState<String> = mutableStateOf("")
    val vmWorldStoryShort: MutableState<String> = mutableStateOf("")
    var vmImageFilename: MutableState<String> = mutableStateOf("")
    var vmFavorite: MutableState<Boolean> = mutableStateOf(false)
    val vmPrice: MutableState<Int> = mutableIntStateOf(0)
    var vmImage: MutableState<Bitmap?> = mutableStateOf(createBitmap(1, 1))
    private lateinit var vmNavController: NavController

    val vmTest: MutableState<Int> = mutableIntStateOf(1)
    val selectedPrimary: MutableState<Int> = mutableIntStateOf(0)
    val selectedSecondary: MutableState<Int> = mutableIntStateOf(0)
    val showCreateUpdateDialog: MutableState<Boolean> = mutableStateOf(false)
    val contentCreateUpdateDialog: MutableState<@Composable () -> Unit> = mutableStateOf(@Composable {})

    init {

    }

    /**
     * Вызывается для сохранения состояния данных об игре
     */
    fun setPropertiesForEntity(entity: EntityBase)
    {
        setPropertiesBlank()
        when(entity)
        {
            is AuthorEntity -> {}
            is TagEntity -> {}
            is GenreEntity -> {}
            is GameEngineEntity -> {}
            is DevEntity -> {}
            is GameEntity -> {
                vmName.value = entity.name
                vmDescription.value = entity.description
                vmComment.value = entity.comment
                vmSubname.value = entity.subname
                vmWorldStoryShort.value = entity.wordStoryShort
                vmImageFilename.value = entity.image
                vmFavorite.value = entity.favorite
                vmPrice.value = entity.price
            }
        }

    }
    fun setPropertiesBlank(){
        vmName.value = ""
        vmDescription.value = ""
        vmComment.value = ""
        vmSubname.value = ""
        vmWorldStoryShort.value = ""
        vmImageFilename.value = ""
        vmFavorite.value = false
        vmPrice.value = 0
        vmImage.value = createBitmap(1, 1)
    }

    fun setNavController(navController: NavController){
        vmNavController = navController
    }
    fun getNavController(): NavController {
        return vmNavController
    }

//    override fun onCleared() {
//        super.onCleared()
//        Repository.onFinish("LibraryViewModel finished")
//    }

}