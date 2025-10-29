package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    var id: Int = 0,
    @ColumnInfo(name = "country_name")
    var name: String = "",
    @ColumnInfo(name = "country_description")
    var description: String = "",
    @ColumnInfo(name = "country_comment")
    var comment: String = "",
)
