package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats

object JOINGameWithCheatsBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, CheatEntity, UGameCheatEntity, POJOGameWithCheats>(){
        override fun setDAO(dao: DBDaoJoin<GameEntity, CheatEntity, UGameCheatEntity, POJOGameWithCheats>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: CheatEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameCheatEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithCheats>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithCheats> {
            return super.getOneLinkedEntity(id)
        }

    }


}