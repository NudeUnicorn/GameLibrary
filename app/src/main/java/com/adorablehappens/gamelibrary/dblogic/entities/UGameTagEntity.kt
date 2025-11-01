package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и TagEntity по id
 *
 * @see GameEntity
 * @see TagEntity
 */
@Entity(tableName = "game_tag_join",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = TagEntity::class,
            parentColumns = ["id"],
            childColumns = ["tagId"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["gameId", "tagId"]
)
data class UGameTagEntity(
    val gameId: Long,
    val tagId: Long
)
