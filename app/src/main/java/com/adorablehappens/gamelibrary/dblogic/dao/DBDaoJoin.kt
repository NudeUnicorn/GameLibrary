package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 * @param T первая сущность
 * @param Y вторая сущность
 * @param U join-сущность(таблица) с id для связывания сущностей
 * @param I pojo дата-класс для получения связанных сущностей
 */
@Dao
interface DBDaoJoin<T, Y, U, I> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertT(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertY(entity: Y)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertJoin(join: U)

    fun getAllLinkedEntities() : LiveData<List<I>>

    fun getOneLinkedEntity(id: Long) : LiveData<I>

}