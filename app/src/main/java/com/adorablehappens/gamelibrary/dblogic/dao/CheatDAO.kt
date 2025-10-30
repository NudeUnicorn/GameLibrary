package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity


interface CheatDAO: DBDaoBehaviour<CheatEntity> {

    @Query("SELECT * FROM cheats")
    override fun getAll(): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE cheat_id=:id OR cheat_name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE cheat_id=:id")
    override fun getOne(id: Long): LiveData<CheatEntity>
    @Insert
    override suspend fun saveNew(entity: CheatEntity)
    @Update
    override suspend fun update(entity: CheatEntity)
    //override fun deleteAll()
    @Delete
    override suspend fun deleteOne(entity: CheatEntity)
}