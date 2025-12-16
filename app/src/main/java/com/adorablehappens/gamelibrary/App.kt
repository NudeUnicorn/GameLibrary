package com.adorablehappens.gamelibrary

import android.app.Application
import com.adorablehappens.gamelibrary.dblogic.Repository
import kotlinx.coroutines.MainScope

class App: Application() {

    val repository: Repository = Repository
    val repositoryCoroutineScope = MainScope()

    init {
    }

    override fun onCreate() {
        super.onCreate()

        repository.initDB(applicationContext, repositoryCoroutineScope, this)

    }


}