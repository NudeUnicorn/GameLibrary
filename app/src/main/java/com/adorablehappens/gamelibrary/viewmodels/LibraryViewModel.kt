package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository

class LibraryViewModel(): ViewModel(){

    //private val gwc: JOINGameWithCheatBEH = Repository.joinGameWithCheatBehaviourRepo

    override fun onCleared() {
        super.onCleared()
        Repository.onFinish("LibraryViewModel finished")
    }
}

val s = LibraryViewModel()