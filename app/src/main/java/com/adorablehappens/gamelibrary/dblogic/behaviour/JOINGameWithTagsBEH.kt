package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameTagEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithTags

object JOINGameWithTagsBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, TagEntity, UGameTagEntity, POJOGameWithTags>(){

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: TagEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameTagEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithTags>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithTags> {
            return super.getOneLinkedEntity(id)
        }

    }


}