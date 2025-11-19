package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository

class LibraryViewModel(): ViewModel() {

    val vmRepo = Repository
    val vmAllGamesLiveData = Repository.AllGamesLiveData


    init {

    }

//    override fun onCleared() {
//        super.onCleared()
//        Repository.onFinish("LibraryViewModel finished")
//    }

}