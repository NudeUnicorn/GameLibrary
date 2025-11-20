package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adorablehappens.gamelibrary.dblogic.dao.DAOAuthor
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCheat
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCountry
import com.adorablehappens.gamelibrary.dblogic.dao.DAODev
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGame
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGameEngine
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGenre
import com.adorablehappens.gamelibrary.dblogic.dao.DAOLanguage
import com.adorablehappens.gamelibrary.dblogic.dao.DAOTag
import com.adorablehappens.gamelibrary.dblogic.dao.DAOWalkthrough
import com.adorablehappens.gamelibrary.dblogic.dao.DAOWalkthroughImage
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoOneFullInfo1
import com.adorablehappens.gamelibrary.dblogic.dao.JOINCheatWithAuthorsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithDevsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithEnginesDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithGenresDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithTagsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithWalkthroughDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINWalkthroughWithImagesDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CountryEntity
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.LanguageEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UCheatAuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameDevsEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameGenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameTagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameWalkthrough
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity

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
        UCheatAuthorEntity::class,
        UGameCheatEntity::class,
        UGameDevsEntity::class,
        UGameEngineEntity::class,
        UGameGenreEntity::class,
        UGameTagEntity::class,
        UGameWalkthrough::class,
        UWalkthroughWithImages::class,
        WalkthroughEntity::class,
        WalkthroughImageEntity::class
               ],
    version = 1
)
abstract class DB: RoomDatabase() {

    abstract fun createAuthorDAO(): DAOAuthor
    abstract fun createCheatDAO(): DAOCheat
    abstract fun createDAOCountry(): DAOCountry
    abstract fun createDAODev(): DAODev
    abstract fun createDAOGame(): DAOGame
    abstract fun createDAOGameEngine(): DAOGameEngine
    abstract fun createDAOGenre(): DAOGenre
    abstract fun createDAOLanguage(): DAOLanguage
    abstract fun createDAOTag(): DAOTag
    abstract fun createDAOWalkthrough(): DAOWalkthrough
    abstract fun createDAOWalkthroughImage(): DAOWalkthroughImage

    abstract fun createJOINCheatWithAuthorsDAO(): JOINCheatWithAuthorsDAO
    abstract fun createJOINGameWithCheatsDAO(): JOINGameWithCheatsDAO
    abstract fun createJOINGameWithDevsDAO(): JOINGameWithDevsDAO
    abstract fun createJOINGameWithEnginesDAO(): JOINGameWithEnginesDAO
    abstract fun createJOINGameWithGenresDAO(): JOINGameWithGenresDAO
    abstract fun createJOINGameWithTagsDAO(): JOINGameWithTagsDAO
    abstract fun createJOINGameWithWalkthroughDAO(): JOINGameWithWalkthroughDAO
    abstract fun createJOINWalkthroughWithImagesDAO(): JOINWalkthroughWithImagesDAO

    //abstract fun createOneGameFullInfoDAO(): DBDaoOneFullInfo

    //abstract fun createOneGameFullInfoDAO(): DBDaoOneFullInfo1


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
                    ).fallbackToDestructiveMigration(true).build()
                    dbInstance = instance
                }
                return instance
            }
        }
    }
}