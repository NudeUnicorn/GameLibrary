package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao

@Dao
interface DBDaoBehaviour <T>{

    /**
     * Возвращает все сущности из таблицы
     */
    fun getAll() : LiveData<List<T>>

    /**
     * Возвращает все сущности из таблицы, где равно идентификатору или имени
     */
    fun getWhere(id: Long, name: String) : LiveData<List<T>>

    /**
     * Возвращает одну сущность, где == id
     */
    fun getOne(id: Long) : LiveData<T>

    /**
     * Сохраняет новую сущность
     */
    suspend fun saveNew(entity: T)

    /**
     * Обновляет сущность, где ==id или сущность саму по себе
     */
    suspend fun update(entity : T)

    /**
     * Удаляет всё из таблицы
     */
    suspend fun deleteAll()

    /**
     * Удаляет одну сущность, где сущность.id == id
     */
    suspend fun deleteOne(entity : T)

}