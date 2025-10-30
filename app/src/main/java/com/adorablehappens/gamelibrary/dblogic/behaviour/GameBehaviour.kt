package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.DBEntityBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity

object GameBehaviour: DBEntityBehaviour<GameEntity, GameDAO> {
    override lateinit var entityList: LiveData<List<GameEntity>>
    override lateinit var entityCurrent: GameEntity
    override lateinit var entityDAO: GameDAO

    override fun getAll(): LiveData<List<GameEntity>> {
        entityList = entityDAO.getAll()

        return entityList
    }

    override fun getWhere(id: Long, name: String): LiveData<List<GameEntity>> {
        return entityDAO.getWhere(id,name)
    }

    override fun getOne(id: Long): LiveData<GameEntity> {
        return entityDAO.getOne(id)
    }

    override suspend fun addNew(entity: GameEntity) {
        entityDAO.addNew(entity)
    }

    override suspend fun update(entity: GameEntity) {
        entityDAO.update(entity)
    }

    override suspend fun deleteAll() {

    }

    override suspend fun deleteOne(entity: GameEntity) {
        entityDAO.deleteOne(entity)
    }

    override fun setDAO(dao: GameDAO) {
        super.setDAO(dao)
    }
}