package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameDevsEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithDevs

@Dao
interface JOINGameWithDevsDAO: DBDaoJoin<GameEntity, DevEntity, UGameDevsEntity, POJOGameWithDevs> {

    @Transaction
    @Query("SELECT * FROM games")
    override fun getAllLinkedEntities(): LiveData<List<POJOGameWithDevs>>

    @Transaction
    @Query("SELECT * FROM games WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithDevs>


}