package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import com.adorablehappens.gamelibrary.dblogic.behaviour.CheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.GameBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var cheatDAORepo: CheatDAO
    val cheatBehaviourRepo: CheatBehaviour = CheatBehaviour
    private lateinit var gameDAORepo: GameDAO
    val gameBehaviourRepo: GameBehaviour = GameBehaviour
    private lateinit var joinGameWithCheatDAORepo: JOINGameWithCheatDAO
    val joinGameWithCheatBehaviourRepo: JOINGameWithCheatBehaviour = JOINGameWithCheatBehaviour

    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context, scope: CoroutineScope){
        db = DB.getInstance(context)
        coroutineScope = scope

        cheatDAORepo = db.getCheatDAO()
        cheatBehaviourRepo.setDAO(cheatDAORepo)
        gameDAORepo = db.getGameDAO()
        gameBehaviourRepo.setDAO(gameDAORepo)
        joinGameWithCheatDAORepo = db.getJOINGameWithCheatDAO()
        joinGameWithCheatBehaviourRepo.setDAO(joinGameWithCheatDAORepo)


    }

    fun getCheatBehaviour(): CheatBehaviour {
        return cheatBehaviourRepo
    }

    fun execCoroutine( func: suspend ()-> Unit){
        coroutineScope.launch {
            func()
        }
    }

    fun onFinish(msg: String = ""){
        coroutineScope.cancel(msg)
    }

}