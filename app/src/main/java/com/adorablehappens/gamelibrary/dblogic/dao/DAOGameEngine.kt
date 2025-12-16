package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity

@Dao
interface DAOGameEngine: DBDao<GameEngineEntity> {

    @Query("SELECT * FROM gameEngines")
    override fun getAll(): LiveData<List<GameEngineEntity>>
    @Query("SELECT * FROM gameEngines WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<GameEngineEntity>>
    @Query("SELECT * FROM gameEngines WHERE id=:id")
    override fun getOne(id: Long): LiveData<GameEngineEntity>
    @Insert
    override fun addNew(entity: GameEngineEntity)
    @Update
    override fun update(entity: GameEngineEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: GameEngineEntity)
}