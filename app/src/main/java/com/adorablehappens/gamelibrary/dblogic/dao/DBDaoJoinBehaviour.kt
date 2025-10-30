package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 * @param D дао-объект
 * @param T первая сущность
 * @param Y вторая сущность
 * @param U join-сущность(таблица) с id для связывания сущностей
 * @param I pojo дата-класс для получения связанных сущностей
 */
@Dao
interface DBDaoJoinBehaviour<D,T,Y,U,I>: DBDaoJoin<T,Y,U,I> {

    var entityDAO: D

    fun setDAO(dao:D){
        entityDAO = dao
    }
}