package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.LanguageEntity

@Dao
interface DAOLanguage: DBDao<LanguageEntity> {

    @Query("SELECT * FROM languages")
    override fun getAll(): LiveData<List<LanguageEntity>>
    @Query("SELECT * FROM languages WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<LanguageEntity>>
    @Query("SELECT * FROM languages WHERE id=:id")
    override fun getOne(id: Long): LiveData<LanguageEntity>
    @Insert
    override fun addNew(entity: LanguageEntity)
    @Update
    override fun update(entity: LanguageEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: LanguageEntity)
}