package com.adorablehappens.gamelibrary.dblogic.behaviour

import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.GameWithCheats

interface JOINGameWithCheatBehaviourI {


    fun setDAO(dao: JOINGameWithCheatDAO)

    fun insertT(entity: GameEntity)

    fun insertY(entity: CheatEntity)

    fun insertJoin(join: UGameCheatEntity)

    fun getAllLinkedEntities(): List<GameWithCheats>

    fun getOneLinkedEntity(id: Long): GameWithCheats
}