package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity

@Dao
interface DAOTag: DBDao<TagEntity> {

    @Query("SELECT * FROM tags")
    override fun getAll(): LiveData<List<TagEntity>>
    @Query("SELECT * FROM tags WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<TagEntity>>
    @Query("SELECT * FROM tags WHERE id=:id")
    override fun getOne(id: Long): LiveData<TagEntity>
    @Insert
    override fun addNew(entity: TagEntity)
    @Update
    override fun update(entity: TagEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: TagEntity)
}