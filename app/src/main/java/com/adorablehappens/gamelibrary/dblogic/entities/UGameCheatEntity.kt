package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и CheatEntity по id
 *
 * @see GameEntity
 * @see CheatEntity
 */
@Entity(tableName = "gameCheat",
    foreignKeys = [
        ForeignKey(
        entity = GameEntity::class,
        parentColumns = ["id"],
        childColumns = ["gameId"],
        onDelete = CASCADE
    ),
        ForeignKey(
            entity = CheatEntity::class,
            parentColumns = ["id"],
            childColumns = ["cheatId"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["gameId", "cheatId"]
    )
data class UGameCheatEntity(
    val gameId: Long,
    val cheatId: Long
)
