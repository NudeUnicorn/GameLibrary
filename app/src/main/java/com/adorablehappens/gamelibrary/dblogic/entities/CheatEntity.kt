package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase

@Entity("cheats")
data class CheatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long,

    @ColumnInfo(name = "name")
    override var name: String = "",

//    @ColumnInfo(name = "author")
//    var author: String = "",
    @ColumnInfo(name = "description")
    override var description: String = "",

    @ColumnInfo(name = "comment")
    override var comment: String = "",
): EntityBase
