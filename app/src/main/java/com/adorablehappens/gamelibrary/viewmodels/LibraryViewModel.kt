package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository

class LibraryViewModel(): ViewModel(){

    //private val gwc: JOINGameWithCheatBEH = Repository.joinGameWithCheatBehaviourRepo
    val vmRepo = Repository
    val vmAllGamesState = Repository.allGamesState

//    val vmGameEntitiesAll by lazy {
//        mutableStateOf(loadGameEntitiesAll())
//    }

    init {

    }

    override fun onCleared() {
        super.onCleared()
        Repository.onFinish("LibraryViewModel finished")
    }

//    private fun vmGetRepoLink(): Repository{
//        return vmRepo
//    }

//    private fun loadGameEntitiesAll(): List<GameEntity> {
//        var toReturn: List<GameEntity> = listOf()
//        vmGameEntitiesAllLive.observe(lifecycleOwner){data ->
//            if (data != null){
//                toReturn = data
//            }
//            else{
//                toReturn = listOf()
//            }
//        }
//        return toReturn
//    }
}

//val s = LibraryViewModel()