package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var id: Int = 0,
    @ColumnInfo(name = "tag_name")
    var name: String = "",
    @ColumnInfo(name = "tag_description")
    var description: String = "",
    @ColumnInfo(name = "tag_comment")
    var comment: String = "",
)
