package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 * @param I pojo дата-класс для получения связанных сущностей
 */
@Dao
interface DBDaoAllOneInfo<I> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun entityInsert(entity: EntityBase)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun entityUpdate(entity: EntityBase)

    @Delete
    fun entityDelete(entity: EntityBase)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun joinInsert(join: JOINEntity)

    @Delete
    fun joinDelete(join: JOINEntity)

    @Query("SELECT * FROM games")
    fun getAllLinkedEntities() : LiveData<List<I>>

    @Query("SELECT * FROM games WHERE id=:id")
    fun getOneLinkedEntity(id: Long) : LiveData<I>

}