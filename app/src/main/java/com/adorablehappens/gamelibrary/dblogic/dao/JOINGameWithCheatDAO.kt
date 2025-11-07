package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats

@Dao
interface JOINGameWithCheatDAO: DBDaoJoin<GameEntity, CheatEntity, UGameCheatEntity, POJOGameWithCheats> {

    @Transaction
    @Query("SELECT * FROM games")
    override fun getAllLinkedEntities(): List<POJOGameWithCheats>

    @Transaction
    @Query("SELECT * FROM games WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): POJOGameWithCheats


}