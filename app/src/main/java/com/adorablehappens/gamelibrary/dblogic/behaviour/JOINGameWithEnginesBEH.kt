package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithEngines

object JOINGameWithEnginesBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, GameEngineEntity, UGameEngineEntity, POJOGameWithEngines>(){
        override fun setDAO(dao: DBDaoJoin<GameEntity, GameEngineEntity, UGameEngineEntity, POJOGameWithEngines>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: GameEngineEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameEngineEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithEngines>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithEngines> {
            return super.getOneLinkedEntity(id)
        }

    }


}