package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameEngines")
data class GameEngineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_engine_id")
    var id: Int = 0,
    @ColumnInfo(name = "game_engine_name")
    var name: String = "",
    @ColumnInfo(name = "game_engine_description")
    var description: String = "",
    @ColumnInfo(name = "game_engine_comment")
    var comment: String = "",
)
