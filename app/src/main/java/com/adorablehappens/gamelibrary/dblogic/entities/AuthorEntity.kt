package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    var id: Long = 0,
    @ColumnInfo(name = "author_name")
    var name: String = "",
    @ColumnInfo(name = "author_description")
    var description: String = "",
    @ColumnInfo(name = "author_comment")
    var comment: String = "",
)
