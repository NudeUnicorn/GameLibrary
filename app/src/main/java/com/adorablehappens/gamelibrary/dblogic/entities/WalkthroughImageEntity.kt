package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "walkthrough_images")
data class WalkthroughImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "path")
    var imagePath: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "comment")
    var comment: String = "",
)
