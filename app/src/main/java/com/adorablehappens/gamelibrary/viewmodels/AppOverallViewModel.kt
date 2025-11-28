package com.adorablehappens.gamelibrary.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.navigation.RoutesMain

class AppOverallViewModel(): ViewModel() {

    val vmRepo = Repository
    val vmAllGamesLiveData = Repository.AllGamesLiveData

    val vmName: MutableState<String?> = mutableStateOf(null)
    val navBarItemSelected = mutableStateOf(RoutesMain.home)
    private lateinit var vmNavController: NavHostController


    init {

    }


    fun setNavHostController(navController: NavHostController){
        vmNavController = navController
    }
    fun getNavHostController(): NavHostController {
        return vmNavController
    }

//    override fun onCleared() {
//        super.onCleared()
//        Repository.onFinish("LibraryViewModel finished")
//    }

}