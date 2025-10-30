package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity

@Database(
    entities = [AuthorEntity::class],
    version = 1
)
abstract class DB: RoomDatabase() {

    abstract fun getAuthorDAO(): AuthorDAO
    abstract fun getCheatDAO(): CheatDAO
    abstract fun getGameDAO(): GameDAO
    abstract fun getJOINGameWithCheatDAO(): JOINGameWithCheatDAO

    companion object{
        private var dbInstance: DB? = null

        /**
         * Создаёт синглтон для объекта базы данных
         */
        fun getInstance(context: Context, dbName: String = "coredb"): DB {
            synchronized(this){
                var instance = dbInstance
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        DB::class.java,
                        dbName
                    ).fallbackToDestructiveMigration(false).build()
                    dbInstance = instance
                }
                return instance
            }
        }
    }
}