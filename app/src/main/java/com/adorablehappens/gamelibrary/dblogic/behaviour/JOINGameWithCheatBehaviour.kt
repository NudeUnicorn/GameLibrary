package com.adorablehappens.gamelibrary.dblogic.behaviour

import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats

object JOINGameWithCheatBehaviour:JOINGameWithCheatBehaviourI{

    private val obj = object : DBDaoJoinBehaviour<GameEntity, CheatEntity, UGameCheatEntity, POJOGameWithCheats>(){}

    override fun setDAO(dao: JOINGameWithCheatDAO){
        obj.setDAO(dao)
    }

    override fun insertT(entity: GameEntity) {
        Repository.execCoroutine {
            obj.insertT(entity)
        }
    }

    override fun insertY(entity: CheatEntity) {
        Repository.execCoroutine {
            obj.insertY(entity)
        }
    }

    override fun insertJoin(join: UGameCheatEntity) {
        Repository.execCoroutine {
            obj.insertJoin(join)
        }
    }

    override fun getAllLinkedEntities(): List<POJOGameWithCheats> {
        return obj.getAllLinkedEntities()
    }

    override fun getOneLinkedEntity(id: Long): POJOGameWithCheats {
        return obj.getOneLinkedEntity(id)
    }
}