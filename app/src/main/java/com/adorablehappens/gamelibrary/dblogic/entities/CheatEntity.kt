package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase

@Entity("cheats")
data class CheatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    var name: String = "",

//    @ColumnInfo(name = "author")
//    var author: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "comment")
    var comment: String = "",
)
