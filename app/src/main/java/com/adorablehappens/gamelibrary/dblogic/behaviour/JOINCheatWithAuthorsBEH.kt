package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UCheatAuthorEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOCheatWithAuthors

object JOINCheatWithAuthorsBEH{

    val obj = object : DBDaoJoinBehaviour<CheatEntity, AuthorEntity, UCheatAuthorEntity, POJOCheatWithAuthors>(){

        override fun insertT(entity: CheatEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: AuthorEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UCheatAuthorEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOCheatWithAuthors>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOCheatWithAuthors> {
            return super.getOneLinkedEntity(id)
        }

    }


}