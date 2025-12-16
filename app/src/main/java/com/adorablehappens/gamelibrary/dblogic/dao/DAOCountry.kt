package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.CountryEntity

@Dao
interface DAOCountry: DBDao<CountryEntity> {

    @Query("SELECT * FROM countries")
    override fun getAll(): LiveData<List<CountryEntity>>
    @Query("SELECT * FROM countries WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<CountryEntity>>
    @Query("SELECT * FROM countries WHERE id=:id")
    override fun getOne(id: Long): LiveData<CountryEntity>
    @Insert
    override fun addNew(entity: CountryEntity)
    @Update
    override fun update(entity: CountryEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: CountryEntity)
}