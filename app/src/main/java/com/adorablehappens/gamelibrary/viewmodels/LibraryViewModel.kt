package com.adorablehappens.gamelibrary.viewmodels

import androidx.lifecycle.ViewModel
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviourI

class LibraryViewModel(private val gwc: JOINGameWithCheatBehaviour = Repository.joinGameWithCheatBehaviour): ViewModel(), JOINGameWithCheatBehaviourI by gwc{

    override fun onCleared() {
        super.onCleared()
        Repository.onFinish("LibraryViewModel finished")
    }
}

val s = LibraryViewModel()