package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.Repository

abstract class DBDaoBehaviour <T>: DBDao<T>{
    protected lateinit var entityDAO : DBDao<T> //здесь тип DAO-класса(например, ProjDAO), сгенерированный объектом базы данных

    /**
     * Передаётся необходимая информация для инициализации и работы в целом
     */
    fun setDAO(dao : DBDao<T>){  //в общем, идея в том, что вызывающая сторона возвращает что-то что тут же присваивается свойству
        entityDAO = dao  //здесь должен вернуться DAO-класс, сгенерированный объектом базы данных
    }

    override fun getAll(): LiveData<List<T>> {
        return entityDAO.getAll()
    }

    override fun getWhere(
        id: Long,
        name: String
    ): LiveData<List<T>> {
        return entityDAO.getWhere(id,name)
    }

    override fun getOne(id: Long): LiveData<T> {
        return entityDAO.getOne(id)
    }

    override fun addNew(entity: T) {
        Repository.execCoroutine {
            entityDAO.addNew(entity)
        }
    }

    override fun update(entity: T) {
        Repository.execCoroutine {
            entityDAO.update(entity)
        }
    }

    override fun deleteOne(entity: T) {
        Repository.execCoroutine {
            entityDAO.deleteOne(entity)
        }
    }
}