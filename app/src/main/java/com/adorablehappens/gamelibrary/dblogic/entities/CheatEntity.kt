package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cheats")
data class CheatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cheat_id")
    var id: Int = 0,
    @ColumnInfo(name = "cheat_name")
    var name: String = "",
//    @ColumnInfo(name = "cheat_author")
//    var author: String = "",
    @ColumnInfo(name = "cheat_description")
    var description: String = "",
    @ColumnInfo(name = "cheat_comment")
    var comment: String = "",
)
