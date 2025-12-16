package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameWalkthrough
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity

data class POJOGameWithWalkthroughes(
    @Embedded
    val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameWalkthrough::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val walkthroughes: List<WalkthroughEntity>
)
