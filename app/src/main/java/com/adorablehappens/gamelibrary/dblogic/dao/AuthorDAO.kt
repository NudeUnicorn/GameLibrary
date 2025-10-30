package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity


interface AuthorDAO: DBDaoBehaviour<AuthorEntity> {

    @Query("SELECT * FROM authors")
    override fun getAll(): LiveData<List<AuthorEntity>>
    @Query("SELECT * FROM authors WHERE author_id=:id OR author_name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<AuthorEntity>>
    @Query("SELECT * FROM authors WHERE author_id=:id")
    override fun getOne(id: Long): LiveData<AuthorEntity>
    @Insert
    override suspend fun saveNew(entity: AuthorEntity)
    @Update
    override suspend fun update(entity: AuthorEntity)
    //override fun deleteAll()
    @Delete
    override suspend fun deleteOne(entity: AuthorEntity)
}