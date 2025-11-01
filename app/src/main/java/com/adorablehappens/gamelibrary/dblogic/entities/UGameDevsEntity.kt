package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и DevEntity по id
 *
 * @see GameEntity
 * @see DevEntity
 */
@Entity(tableName = "game_dev_join",
    primaryKeys = ["gameId", "devId"],
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = DevEntity::class,
            parentColumns = ["id"],
            childColumns = ["devId"],
            onDelete = CASCADE
        )
    ],
    )
data class UGameDevsEntity(
    val gameId: Long,
    val devId: Long
)
