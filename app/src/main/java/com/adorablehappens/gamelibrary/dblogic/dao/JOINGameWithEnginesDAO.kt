package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithEngines

@Dao
interface JOINGameWithEnginesDAO: DBDaoJoin<GameEntity, GameEngineEntity, UGameEngineEntity, POJOGameWithEngines> {

    @Transaction
    @Query("SELECT * FROM games")
    override fun getAllLinkedEntities(): LiveData<List<POJOGameWithEngines>>

    @Transaction
    @Query("SELECT * FROM games WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithEngines>


}