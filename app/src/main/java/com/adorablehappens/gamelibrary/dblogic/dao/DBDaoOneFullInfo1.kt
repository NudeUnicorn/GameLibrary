package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.AllInfo
import com.adorablehappens.gamelibrary.dblogic.pojo.OneGameFullInfo1

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 */
@Dao
interface DBDaoOneFullInfo1 {

    /**
     * Возвращает все связанные сущности из таблицы
     */

    @Query("""
        SELECT *
FROM games games

LEFT JOIN game_cheat_join gcJ ON games.id = gcJ.parentEntityID
LEFT JOIN cheats cheats ON cheats.id = gcJ.childEntityID

LEFT JOIN game_genre_join ggJ ON games.id = ggJ.parentEntityID
LEFT JOIN genres genres ON genres.id = ggJ.childEntityID

LEFT JOIN game_tag_join gtJ ON games.id = gtJ.parentEntityID
LEFT JOIN tags tags ON tags.id = gtJ.childEntityID

LEFT JOIN game_dev_join gdJ ON games.id = gdJ.parentEntityID
LEFT JOIN devs devs ON devs.id = gdJ.childEntityID

LEFT JOIN game_engine_join geJ ON games.id = geJ.parentEntityID
LEFT JOIN gameEngines gameEngines ON gameEngines.id = geJ.childEntityID

LEFT JOIN game_walkthrough_join gwJ ON games.id = gwJ.parentEntityID
LEFT JOIN walkthroughes walkthroughes ON walkthroughes.id = gwJ.childEntityID

ORDER BY games.id
""")
    fun getAllLinkedEntities() : LiveData<List<OneGameFullInfo1>>

    /**
     * Возвращает одну сущность из таблицы
     */
    @Query("""
        SELECT *
FROM games games 

LEFT JOIN game_cheat_join gcJ ON games.id = gcJ.parentEntityID
LEFT JOIN cheats cheats ON cheats.id = gcJ.childEntityID

LEFT JOIN game_genre_join ggJ ON games.id = ggJ.parentEntityID
LEFT JOIN genres genres ON genres.id = ggJ.childEntityID

LEFT JOIN game_tag_join gtJ ON games.id = gtJ.parentEntityID
LEFT JOIN tags tags ON tags.id = gtJ.childEntityID

LEFT JOIN game_dev_join gdJ ON games.id = gdJ.parentEntityID
LEFT JOIN devs devs ON devs.id = gdJ.childEntityID

LEFT JOIN game_engine_join geJ ON games.id = geJ.parentEntityID
LEFT JOIN gameEngines gameEngines ON gameEngines.id = geJ.childEntityID

LEFT JOIN game_walkthrough_join gwJ ON games.id = gwJ.parentEntityID
LEFT JOIN walkthroughes walkthroughes ON walkthroughes.id = gwJ.childEntityID

WHERE games.id = :id
""")
    fun getOneLinkedEntity(id: Long) : LiveData<OneGameFullInfo1>

    /**
     * Возвращает все общие сущности из таблицы
     */
    @Query("""
        SELECT *
FROM 
genres genres,
tags tags,
devs devs,
gameEngines gameEngines

""")
    fun getAllInfo() : LiveData<AllInfo>

}