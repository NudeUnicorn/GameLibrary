package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 * @param T первая сущность
 * @param Y вторая сущность
 * @param U join-сущность(таблица) с id для связывания сущностей
 * @param I pojo дата-класс для получения связанных сущностей
 */
@Dao
interface DBDaoAllOneInfo<T, Y, U, I> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun joinInsert(join: JOINEntity)

    @Delete
    fun joinDelete(join: JOINEntity)

    @Query("SELECT * FROM games")
    fun getAllLinkedEntities() : LiveData<List<I>>

    @Query("SELECT * FROM games WHERE id=:id")
    fun getOneLinkedEntity(id: Long) : LiveData<I>

}