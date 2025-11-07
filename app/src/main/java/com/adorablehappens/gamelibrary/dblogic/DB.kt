package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoOneFullInfo
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
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
import com.adorablehappens.gamelibrary.dblogic.entities.UAuthorCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameDevsEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameGenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameTagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.OneGameFullInfo

@Database(
    entities = [
//        JOINEntity::class,
//        EntityBase::class,
        AuthorEntity::class,
        CheatEntity::class,
        CountryEntity::class,
        DevEntity::class,
        GameEngineEntity::class,
        GameEntity::class,
        GenreEntity::class,
        LanguageEntity::class,
        TagEntity::class,
        UAuthorCheatEntity::class,
        UGameCheatEntity::class,
        UGameDevsEntity::class,
        UGameEngineEntity::class,
        UGameGenreEntity::class,
        UGameTagEntity::class,
        UWalkthroughWithImages::class,
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
    //abstract fun createOneGameFullInfoDAO(): DBDaoOneFullInfo

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