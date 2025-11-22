package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase

@Entity(tableName = "walkthrough_images")
data class WalkthroughImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long,

    @ColumnInfo(name = "name")
    override var name: String = "",

    @ColumnInfo(name = "path")
    var imagePath: String = "",

    @ColumnInfo(name = "description")
    override var description: String = "",

    @ColumnInfo(name = "comment")
    override var comment: String = "",
): EntityBase