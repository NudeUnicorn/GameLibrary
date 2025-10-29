package com.adorablehappens.gamelibrary.dblogic

import android.content.Context

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB

    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context){
        db = DB.getInstance(context)
    }

}