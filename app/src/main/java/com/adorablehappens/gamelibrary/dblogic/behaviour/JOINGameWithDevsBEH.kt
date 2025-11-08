package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameDevsEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithDevs

object JOINGameWithDevsBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, DevEntity, UGameDevsEntity, POJOGameWithDevs>(){
        override fun setDAO(dao: DBDaoJoin<GameEntity, DevEntity, UGameDevsEntity, POJOGameWithDevs>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: DevEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameDevsEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithDevs>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithDevs> {
            return super.getOneLinkedEntity(id)
        }

    }


}