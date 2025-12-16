package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity

@Dao
interface DAODev: DBDao<DevEntity> {

    @Query("SELECT * FROM devs")
    override fun getAll(): LiveData<List<DevEntity>>
    @Query("SELECT * FROM devs WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<DevEntity>>
    @Query("SELECT * FROM devs WHERE id=:id")
    override fun getOne(id: Long): LiveData<DevEntity>
    @Insert
    override fun addNew(entity: DevEntity)
    @Update
    override fun update(entity: DevEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: DevEntity)
}