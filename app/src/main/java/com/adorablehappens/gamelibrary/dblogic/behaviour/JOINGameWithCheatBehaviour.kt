package com.adorablehappens.gamelibrary.dblogic.behaviour

import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.GameWithCheats

object JOINGameWithCheatBehaviour: DBDaoJoinBehaviour<JOINGameWithCheatDAO, GameEntity, CheatEntity, UGameCheatEntity, GameWithCheats> {

    override lateinit var entityDAO: JOINGameWithCheatDAO

    override suspend fun insertT(entity: GameEntity) {
        entityDAO.insertT(entity)
    }

    override suspend fun insertY(entity: CheatEntity) {
        entityDAO.insertY(entity)
    }

    override suspend fun insertJoin(join: UGameCheatEntity) {
        entityDAO.insertJoin(join)
    }

    override fun getAllLinkedEntities(): List<GameWithCheats> {
        return entityDAO.getAllLinkedEntities()
    }

    override fun getOneLinkedEntity(id: Long): GameWithCheats {
        return entityDAO.getOneLinkedEntity(id)
    }
}