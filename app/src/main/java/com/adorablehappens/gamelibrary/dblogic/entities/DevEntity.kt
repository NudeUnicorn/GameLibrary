package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devs")
data class DevEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dev_id")
    var id: Int = 0,
    @ColumnInfo(name = "dev_name")
    var name: String = "",
    @ColumnInfo(name = "dev_description")
    var description: String = "",
    @ColumnInfo(name = "dev_comment")
    var comment: String = "",
)
