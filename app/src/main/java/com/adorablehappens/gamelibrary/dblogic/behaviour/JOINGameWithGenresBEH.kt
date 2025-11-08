package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameGenreEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithGenres

object JOINGameWithGenresBEH{

    val obj = object : DBDaoJoinBehaviour<GameEntity, GenreEntity, UGameGenreEntity, POJOGameWithGenres>(){
        override fun setDAO(dao: DBDaoJoin<GameEntity, GenreEntity, UGameGenreEntity, POJOGameWithGenres>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: GameEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: GenreEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UGameGenreEntity) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOGameWithGenres>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithGenres> {
            return super.getOneLinkedEntity(id)
        }

    }


}