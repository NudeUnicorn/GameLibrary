package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatsDAO
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats

interface DBDaoJoinBase {


    fun setDAO(dao: JOINGameWithCheatsDAO)

    fun insertT(entity: GameEntity)

    fun insertY(entity: CheatEntity)

    fun insertJoin(join: UGameCheatEntity)

    fun getAllLinkedEntities(): LiveData<List<POJOGameWithCheats>>

    fun getOneLinkedEntity(id: Long): LiveData<POJOGameWithCheats>
}