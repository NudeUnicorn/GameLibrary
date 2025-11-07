package com.adorablehappens.gamelibrary.dblogic.behaviour

import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats

interface JOINGameWithCheatBehaviourI {


    fun setDAO(dao: JOINGameWithCheatDAO)

    fun insertT(entity: GameEntity)

    fun insertY(entity: CheatEntity)

    fun insertJoin(join: UGameCheatEntity)

    fun getAllLinkedEntities(): List<POJOGameWithCheats>

    fun getOneLinkedEntity(id: Long): POJOGameWithCheats
}