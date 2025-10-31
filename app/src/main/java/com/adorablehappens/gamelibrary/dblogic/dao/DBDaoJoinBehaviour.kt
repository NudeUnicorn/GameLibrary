package com.adorablehappens.gamelibrary.dblogic.dao

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

    fun setDAO(dao:DBDaoJoin<T,Y,U,I>){
        entityDAO = dao
    }

    override suspend fun insertT(entity: T) {
        entityDAO.insertT(entity)
    }

    override suspend fun insertY(entity: Y) {
        entityDAO.insertY(entity)
    }

    override suspend fun insertJoin(join: U) {
        entityDAO.insertJoin(join)
    }

    override fun getAllLinkedEntities(): List<I> {
        return entityDAO.getAllLinkedEntities()
    }

    override fun getOneLinkedEntity(id: Long): I {
        return entityDAO.getOneLinkedEntity(id)
    }
}