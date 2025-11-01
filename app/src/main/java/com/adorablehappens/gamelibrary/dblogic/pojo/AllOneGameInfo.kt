package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
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
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages

/**
 * POJO-класс для сбора всей информации из базы данных об одной игре
 */
data class AllOneGameInfo(
    @Embedded
    val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameCheatEntity::class,
            parentColumn = "gameId",
            entityColumn = "cheatId"
        )
    )
    val cheats: List<CheatWithAuthors>,

    @Embedded("genre_")
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameGenreEntity::class,
            parentColumn = "gameId",
            entityColumn = "genreId"
        )
    )
    val genres: List<GenreEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameTagEntity::class,
            parentColumn = "gameId",
            entityColumn = "tagId"
        )
    )
    val tags: List<TagEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameDevsEntity::class,
            parentColumn = "gameId",
            entityColumn = "devId"
        )
    )
    val devs: List<DevEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UGameEngineEntity::class,
            parentColumn = "gameId",
            entityColumn = "gameEngineId"
        )
    )
    val gEngines: List<GameEngineEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UWalkthroughWithImages::class,
            parentColumn = "gameId",
            entityColumn = "walkthroughId"
        )
    )
    val walkthroughes: List<WalkthroughWithImages>,

    )
