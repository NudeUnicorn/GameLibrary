package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity

data class POJOGameWithEngines(
    @Embedded
    val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameEngineEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val cheats: List<GameEngineEntity>
)
