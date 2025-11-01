package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UAuthorCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity

data class CheatWithAuthors(
    @Embedded
    val cheat: CheatEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UAuthorCheatEntity::class,
            parentColumn = "cheatId",
            entityColumn = "authorId"
        )
    )
    val authors: List<AuthorEntity>
)
