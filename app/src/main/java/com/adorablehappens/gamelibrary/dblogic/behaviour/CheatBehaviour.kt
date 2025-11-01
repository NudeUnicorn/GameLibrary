package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity

object CheatBehaviour: DBDaoBehaviour<CheatEntity, CheatDAO> {
    override lateinit var entityList: LiveData<List<CheatEntity>>
    override lateinit var entityCurrent: CheatEntity
    override lateinit var entityDAO: CheatDAO

    override fun getAll(): LiveData<List<CheatEntity>> {
        entityList = entityDAO.getAll()

        return entityList
    }

    override fun getWhere(id: Long, name: String): LiveData<List<CheatEntity>> {
        return entityDAO.getWhere(id,name)
    }

    override fun getOne(id: Long): LiveData<CheatEntity> {
        return entityDAO.getOne(id)
    }

    override suspend fun addNew(entity: CheatEntity) {
        entityDAO.addNew(entity)
    }

    override suspend fun update(entity: CheatEntity) {
        entityDAO.update(entity)
    }

    override suspend fun deleteOne(entity: CheatEntity) {
        entityDAO.deleteOne(entity)
    }

    override fun setDAO(dao: CheatDAO) {
        super.setDAO(dao)
    }
}