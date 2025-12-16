package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.Repository

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 * @param T первая сущность
 * @param Y вторая сущность
 * @param U join-сущность(таблица) с id для связывания сущностей
 * @param I pojo дата-класс для получения связанных сущностей
 */

abstract class DBDaoJoinBehaviour <T,Y,U,I>: DBDaoJoin<T,Y,U,I> {

    protected lateinit var entityDAO: DBDaoJoin<T,Y,U,I>

    open fun setDAO(dao:DBDaoJoin<T,Y,U,I>){
        entityDAO = dao
    }

    override fun insertT(entity: T) {
        Repository.execCoroutine {
            entityDAO.insertT(entity)
        }
    }

    override fun insertY(entity: Y) {
        Repository.execCoroutine {
            entityDAO.insertY(entity)
        }
    }

    override fun insertJoin(join: U) {
        Repository.execCoroutine {
            entityDAO.insertJoin(join)
        }
    }

    override fun getAllLinkedEntities(): LiveData<List<I>> {
        return entityDAO.getAllLinkedEntities()
    }

    override fun getOneLinkedEntity(id: Long): LiveData<I> {
        return entityDAO.getOneLinkedEntity(id)
    }
}