package com.adorablehappens.gamelibrary.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity

class LibraryViewModel(val lifecycleOwner: LifecycleOwner): ViewModel(){

    //private val gwc: JOINGameWithCheatBEH = Repository.joinGameWithCheatBehaviourRepo
    private val gameEntitiesAllLive = Repository.gameEntitiesLazy
    val gameEntitiesAll by lazy {
        mutableStateOf(loadGameEntitiesAll())
    }

    init {

    }

    override fun onCleared() {
        super.onCleared()
        Repository.onFinish("LibraryViewModel finished")
    }
    private fun loadGameEntitiesAll(): List<GameEntity> {
        var toReturn: List<GameEntity> = listOf()
        gameEntitiesAllLive.observe(lifecycleOwner){data ->
            if (data != null){
                toReturn = data
            }
            else{
                toReturn = listOf()
            }
        }
        return toReturn
    }
}

//val s = LibraryViewModel()