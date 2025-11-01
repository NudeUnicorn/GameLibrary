package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity

@Dao
interface CheatDAO: DBDao<CheatEntity> {

    @Query("SELECT * FROM cheats")
    override fun getAll(): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE cheatId=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE cheatId=:id")
    override fun getOne(id: Long): LiveData<CheatEntity>
    @Insert
    override suspend fun addNew(entity: CheatEntity)
    @Update
    override suspend fun update(entity: CheatEntity)
    //override fun deleteAll()
    @Delete
    override suspend fun deleteOne(entity: CheatEntity)
}