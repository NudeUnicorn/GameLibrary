package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity

@Database(
    entities = [AuthorEntity::class],
    version = 1
)
abstract class DB: RoomDatabase() {

    abstract fun getAuthorDAO(): AuthorDAO

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