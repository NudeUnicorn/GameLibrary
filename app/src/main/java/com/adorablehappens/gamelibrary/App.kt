package com.adorablehappens.gamelibrary

import android.app.Application
import com.adorablehappens.gamelibrary.dblogic.Repository

class App: Application() {

    val repository = Repository

    init {
        repository.initDB(this)
    }
}