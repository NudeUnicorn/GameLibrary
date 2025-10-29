package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица для связи GameEntity и TagEntity
 */
@Entity(tableName = "gameCheat",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["game_id"],
            childColumns = ["gameId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["tag_id"],
            childColumns = ["tagId"],
            onDelete = CASCADE
        )
    ]
)
data class UGameTagEntity(
    var gameId: Long,
    var tagId: Long
)
