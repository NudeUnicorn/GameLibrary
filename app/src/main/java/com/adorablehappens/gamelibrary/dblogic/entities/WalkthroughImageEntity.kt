package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "walkthrough_images")
data class WalkthroughImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walkthrough_image_id")
    var id: Int = 0,
    @ColumnInfo(name = "walkthrough_image_name")
    var name: String = "",
    @ColumnInfo(name = "walkthrough_image_path")
    var imagePath: String = "",
    @ColumnInfo(name = "walkthrough_image_description")
    var description: String = "",
    @ColumnInfo(name = "walkthrough_image_comment")
    var comment: String = "",
)
