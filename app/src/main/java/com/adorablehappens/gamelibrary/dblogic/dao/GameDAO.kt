package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity


interface GameDAO: DBDaoBehaviour<GameEntity> {

    @Query("SELECT * FROM games")
    override fun getAll(): LiveData<List<GameEntity>>
    @Query("SELECT * FROM games WHERE game_id=:id OR game_name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<GameEntity>>
    @Query("SELECT * FROM games WHERE game_id=:id")
    override fun getOne(id: Long): LiveData<GameEntity>
    @Insert
    override suspend fun saveNew(entity: GameEntity)
    @Update
    override suspend fun update(entity: GameEntity)
    //override fun deleteAll()
    @Delete
    override suspend fun deleteOne(entity: GameEntity)
}