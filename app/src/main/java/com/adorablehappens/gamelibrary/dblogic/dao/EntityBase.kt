package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
abstract class EntityBase(
    @PrimaryKey(autoGenerate = true)
    open val id: Long,
    open var name: String = "",
    open var description: String = "",
    open var comment: String = "",
) {
}