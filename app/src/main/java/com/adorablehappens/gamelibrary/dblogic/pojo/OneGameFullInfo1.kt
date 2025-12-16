package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
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
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity

/**
 * POJO-класс для сбора всей информации из базы данных об одной игре
 */
data class OneGameFullInfo1(

    @Embedded("games_")
    val games: List<GameEntity>,

    @Embedded("cheats_")
    val cheats: List<CheatEntity>,

    @Embedded("genre_")
    val genres: List<GenreEntity>,

    @Embedded("tag_")
    val tags: List<TagEntity>,

    @Embedded("dev_")
    val devs: List<DevEntity>,

    @Embedded("gameEngine_")
    val gameEngines: List<GameEngineEntity>,

    @Embedded("walkthrough_")
    val walkthroughes: List<WalkthroughEntity>,

    )
