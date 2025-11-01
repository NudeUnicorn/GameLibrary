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
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CountryEntity
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.LanguageEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameTagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity

@Database(
    entities = [
        AuthorEntity::class,
        CheatEntity::class,
        CountryEntity::class,
        DevEntity::class,
        GameEngineEntity::class,
        GameEntity::class,
        GenreEntity::class,
        LanguageEntity::class,
        TagEntity::class,
        UGameCheatEntity::class,
        UGameTagEntity::class,
        WalkthroughEntity::class,
        WalkthroughImageEntity::class
               ],
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