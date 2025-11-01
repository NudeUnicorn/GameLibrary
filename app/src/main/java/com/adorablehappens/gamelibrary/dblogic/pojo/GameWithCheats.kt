package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity

data class GameWithCheats(
    @Embedded
    val game: GameEntity,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "cheatId",
        associateBy = Junction(UGameCheatEntity::class)
    )
    val cheats: List<CheatEntity>
)
