package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.adorablehappens.gamelibrary.dblogic.Repository

class LibraryViewModel(): ViewModel() {

    val vmRepo = Repository
    val vmAllGamesLiveData = Repository.AllGamesLiveData
    private lateinit var vmNavController: NavController


    init {

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