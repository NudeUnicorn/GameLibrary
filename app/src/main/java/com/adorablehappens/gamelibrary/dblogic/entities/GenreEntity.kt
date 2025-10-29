package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id")
    var id: Int = 0,
    @ColumnInfo(name = "genre_name")
    var name: String = "",
    @ColumnInfo(name = "genre_description")
    var description: String = "",
    @ColumnInfo(name = "genre_comment")
    var comment: String = "",
)
