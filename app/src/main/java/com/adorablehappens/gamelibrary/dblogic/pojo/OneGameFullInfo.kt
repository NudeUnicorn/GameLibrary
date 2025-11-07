package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameDevsEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameGenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameTagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameWalkthrough
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages

/**
 * POJO-класс для сбора всей информации из базы данных об одной игре
 */
data class OneGameFullInfo(
    @Embedded
    val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameCheatEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val cheats: List<POJOCheatWithAuthors>,

    //@Embedded("genre_")
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameGenreEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val genres: List<GenreEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameTagEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val tags: List<TagEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameDevsEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val devs: List<DevEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameEngineEntity::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val gEngines: List<GameEngineEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameWalkthrough::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val walkthroughes: List<POJOWalkthroughWithImages>,

    )
