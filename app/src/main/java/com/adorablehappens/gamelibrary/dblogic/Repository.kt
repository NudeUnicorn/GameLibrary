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
    private lateinit var cheatDAO: CheatDAO
    val cheatBehaviour: CheatBehaviour = CheatBehaviour
    private lateinit var gameDAO: GameDAO
    val gameBehaviour: GameBehaviour = GameBehaviour
    private lateinit var joinGameWithCheatDAO: JOINGameWithCheatDAO
    val joinGameWithCheatBehaviour: JOINGameWithCheatBehaviour = JOINGameWithCheatBehaviour

    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context, scope: CoroutineScope){
        db = DB.getInstance(context)
        coroutineScope = scope

        cheatDAO = db.getCheatDAO()
        cheatBehaviour.setDAO(cheatDAO)
        gameDAO = db.getGameDAO()
        gameBehaviour.setDAO(gameDAO)
        joinGameWithCheatDAO = db.getJOINGameWithCheatDAO()
        joinGameWithCheatBehaviour.setDAO(joinGameWithCheatDAO)


    }

    fun getCheatBehaviour(): CheatBehaviour {
        return cheatBehaviour
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