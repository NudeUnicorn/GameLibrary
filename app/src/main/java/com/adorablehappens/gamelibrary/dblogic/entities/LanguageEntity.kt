package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "language_id")
    var id: Int = 0,
    @ColumnInfo(name = "language_name")
    var name: String = "",
    @ColumnInfo(name = "language_description")
    var description: String = "",
    @ColumnInfo(name = "language_comment")
    var comment: String = "",
)
