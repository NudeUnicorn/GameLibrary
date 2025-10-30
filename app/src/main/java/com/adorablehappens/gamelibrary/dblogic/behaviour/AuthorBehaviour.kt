package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity

object AuthorBehaviour: DBDaoBehaviour<AuthorEntity, AuthorDAO> {
    override lateinit var entityList: LiveData<List<AuthorEntity>>
    override lateinit var entityCurrent: AuthorEntity
    override lateinit var entityDAO: AuthorDAO

    override fun getAll(): LiveData<List<AuthorEntity>> {
        entityList = entityDAO.getAll()

        return entityList
    }

    override fun getWhere(id: Long, name: String): LiveData<List<AuthorEntity>> {
        return entityDAO.getWhere(id,name)
    }

    override fun getOne(id: Long): LiveData<AuthorEntity> {
        return entityDAO.getOne(id)
    }

    override suspend fun addNew(entity: AuthorEntity) {
        entityDAO.addNew(entity)
    }

    override suspend fun update(entity: AuthorEntity) {
        entityDAO.update(entity)
    }

    override suspend fun deleteAll() {

    }

    override suspend fun deleteOne(entity: AuthorEntity) {
        entityDAO.deleteOne(entity)
    }

    override fun setDAO(dao: AuthorDAO) {
        super.setDAO(dao)
    }
}