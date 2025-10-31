package com.adorablehappens.gamelibrary.dblogic.behaviour

import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.GameWithCheats

object JOINGameWithCheatBehaviour{

    private val obj = object : DBDaoJoinBehaviour<GameEntity, CheatEntity, UGameCheatEntity, GameWithCheats>(){}

    fun setDAO(dao: JOINGameWithCheatDAO){
        obj.setDAO(dao)
    }

    fun insertT(entity: GameEntity) {
        Repository.execCoroutine {
            obj.insertT(entity)
        }
    }

    fun insertY(entity: CheatEntity) {
        Repository.execCoroutine {
            obj.insertY(entity)
        }
    }

    fun insertJoin(join: UGameCheatEntity) {
        Repository.execCoroutine {
            obj.insertJoin(join)
        }
    }

    fun getAllLinkedEntities(): List<GameWithCheats> {
        return obj.getAllLinkedEntities()
    }

    fun getOneLinkedEntity(id: Long): GameWithCheats {
        return obj.getOneLinkedEntity(id)
    }
}