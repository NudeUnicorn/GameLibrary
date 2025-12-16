package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity

@Dao
interface DAOCheat: DBDao<CheatEntity> {

    @Query("SELECT * FROM cheats")
    override fun getAll(): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<CheatEntity>>
    @Query("SELECT * FROM cheats WHERE id=:id")
    override fun getOne(id: Long): LiveData<CheatEntity>
    @Insert
    override fun addNew(entity: CheatEntity)
    @Update
    override fun update(entity: CheatEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: CheatEntity)
}