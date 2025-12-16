package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameWalkthrough
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithWalkthroughes

object JOINGameWithWalkthroughesBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, WalkthroughEntity, UGameWalkthrough, POJOGameWithWalkthroughes>(){
        override fun setDAO(dao: DBDaoJoin<GameEntity, WalkthroughEntity, UGameWalkthrough, POJOGameWithWalkthroughes>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: WalkthroughEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameWalkthrough) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithWalkthroughes>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithWalkthroughes> {
            return super.getOneLinkedEntity(id)
        }

    }


}