package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao


interface DBDao <T>{

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
    fun addNew(entity: T)

    /**
     * Обновляет сущность, где ==id или сущность саму по себе
     */
    fun update(entity : T)

    /**
     * Удаляет одну сущность, где сущность.id == id
     */
    fun deleteOne(entity : T)


}