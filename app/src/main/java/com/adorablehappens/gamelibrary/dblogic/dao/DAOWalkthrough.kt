package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity

@Dao
interface DAOWalkthrough: DBDao<WalkthroughEntity> {

    @Query("SELECT * FROM walkthroughes")
    override fun getAll(): LiveData<List<WalkthroughEntity>>
    @Query("SELECT * FROM walkthroughes WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<WalkthroughEntity>>
    @Query("SELECT * FROM walkthroughes WHERE id=:id")
    override fun getOne(id: Long): LiveData<WalkthroughEntity>
    @Insert
    override fun addNew(entity: WalkthroughEntity)
    @Update
    override fun update(entity: WalkthroughEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: WalkthroughEntity)
}