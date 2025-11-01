package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviourI

class LibraryViewModel(): ViewModel(){

    private val gwc: JOINGameWithCheatBehaviourI = Repository.joinGameWithCheatBehaviourRepo

    override fun onCleared() {
        super.onCleared()
        Repository.onFinish("LibraryViewModel finished")
    }
}

val s = LibraryViewModel()