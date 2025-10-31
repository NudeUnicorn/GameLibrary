package com.adorablehappens.gamelibrary

import android.app.Application
import com.adorablehappens.gamelibrary.dblogic.Repository
import kotlinx.coroutines.MainScope

class App: Application() {

    val repository = Repository
    val repositoryCoroutineScope = MainScope()

    init {
        repository.initDB(this, repositoryCoroutineScope)
    }



}