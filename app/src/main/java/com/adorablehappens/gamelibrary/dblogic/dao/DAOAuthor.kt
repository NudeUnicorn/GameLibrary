package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity

@Dao
interface DAOAuthor: DBDao<AuthorEntity> {

    @Query("SELECT * FROM authors")
    override fun getAll(): LiveData<List<AuthorEntity>>
    @Query("SELECT * FROM authors WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<AuthorEntity>>
    @Query("SELECT * FROM authors WHERE id=:id")
    override fun getOne(id: Long): LiveData<AuthorEntity>
    @Insert
    override fun addNew(entity: AuthorEntity)
    @Update
    override fun update(entity: AuthorEntity)
    @Delete
    override fun deleteOne(entity: AuthorEntity)
}