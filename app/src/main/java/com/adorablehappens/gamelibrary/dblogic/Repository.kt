package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import com.adorablehappens.gamelibrary.dblogic.behaviour.CheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.GameBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB
    private lateinit var cheatDAO: CheatDAO
    val cheatBehaviour: CheatBehaviour = CheatBehaviour
    private lateinit var gameDAO: GameDAO
    val gameBehaviour: GameBehaviour = GameBehaviour
    private lateinit var joinGameWithCheatDAO: JOINGameWithCheatDAO

    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context){
        db = DB.getInstance(context)

        cheatDAO = db.getCheatDAO()
        cheatBehaviour.setDAO(cheatDAO)
        gameDAO = db.getGameDAO()
        gameBehaviour.setDAO(gameDAO)
        joinGameWithCheatDAO = db.getJOINGameWithCheatDAO()
    }

    fun getCheatBehaviour(): CheatBehaviour {
        return cheatBehaviour
    }

}