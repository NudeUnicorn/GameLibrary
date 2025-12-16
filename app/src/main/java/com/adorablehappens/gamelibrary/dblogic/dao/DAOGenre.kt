package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity

@Dao
interface DAOGenre: DBDao<GenreEntity> {

    @Query("SELECT * FROM genres")
    override fun getAll(): LiveData<List<GenreEntity>>
    @Query("SELECT * FROM genres WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<GenreEntity>>
    @Query("SELECT * FROM genres WHERE id=:id")
    override fun getOne(id: Long): LiveData<GenreEntity>
    @Insert
    override fun addNew(entity: GenreEntity)
    @Update
    override fun update(entity: GenreEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: GenreEntity)
}