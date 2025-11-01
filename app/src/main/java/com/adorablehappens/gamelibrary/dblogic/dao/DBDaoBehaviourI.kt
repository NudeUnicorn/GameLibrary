package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData

interface DBDaoBehaviourI <T>{

    /**
     * Передаётся необходимая информация для инициализации и работы в целом. Например, ссылка на объект БД или репозитория
     */
    fun setDAO(dao : DBDao<T>)

    fun getAll(): LiveData<List<T>>

    fun getWhere(
        id: Long,
        name: String
    ): LiveData<List<T>>

    fun getOne(id: Long): LiveData<T>

    suspend fun addNew(entity: T)

    suspend fun update(entity: T)

    suspend fun deleteOne(entity: T)
}