package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity

@Dao
interface DAOWalkthroughImage: DBDao<WalkthroughImageEntity> {

    @Query("SELECT * FROM walkthrough_images")
    override fun getAll(): LiveData<List<WalkthroughImageEntity>>
    @Query("SELECT * FROM walkthrough_images WHERE id=:id OR name=:name")
    override fun getWhere(id: Long, name: String): LiveData<List<WalkthroughImageEntity>>
    @Query("SELECT * FROM walkthrough_images WHERE id=:id")
    override fun getOne(id: Long): LiveData<WalkthroughImageEntity>
    @Insert
    override fun addNew(entity: WalkthroughImageEntity)
    @Update
    override fun update(entity: WalkthroughImageEntity)
    //override fun deleteAll()
    @Delete
    override fun deleteOne(entity: WalkthroughImageEntity)
}