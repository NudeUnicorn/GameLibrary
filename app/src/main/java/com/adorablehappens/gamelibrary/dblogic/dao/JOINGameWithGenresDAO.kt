package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameGenreEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithGenres

@Dao
interface JOINGameWithGenresDAO: DBDaoJoin<GameEntity, GenreEntity, UGameGenreEntity, POJOGameWithGenres> {

    @Transaction
    @Query("SELECT * FROM games")
    override fun getAllLinkedEntities(): LiveData<List<POJOGameWithGenres>>

    @Transaction
    @Query("SELECT * FROM games WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithGenres>


}