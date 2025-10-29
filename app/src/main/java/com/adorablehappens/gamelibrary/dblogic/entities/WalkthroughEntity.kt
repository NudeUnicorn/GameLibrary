package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "walkthroughes")
data class WalkthroughEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walkthrough_id")
    var id: Int = 0,
    @ColumnInfo(name = "walkthrough_name")
    var name: String = "",
    @ColumnInfo(name = "walkthrough_description")
    var description: String = "",
    @ColumnInfo(name = "walkthrough_comment")
    var comment: String = "",
)
