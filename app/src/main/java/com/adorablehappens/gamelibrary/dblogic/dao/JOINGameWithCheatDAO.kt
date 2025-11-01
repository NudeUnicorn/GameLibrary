package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.GameWithCheats

@Dao
interface JOINGameWithCheatDAO: DBDaoJoin<GameEntity, CheatEntity, UGameCheatEntity, GameWithCheats> {

    @Transaction
    @Query("SELECT * FROM games")
    override fun getAllLinkedEntities(): List<GameWithCheats>

    @Transaction
    @Query("SELECT * FROM games WHERE gameId=:id")
    override fun getOneLinkedEntity(id: Long): GameWithCheats


}