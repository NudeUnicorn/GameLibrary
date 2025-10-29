package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица для связи GameEntity и CheatEntity
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
            entity = CheatEntity::class,
            parentColumns = ["cheat_id"],
            childColumns = ["cheatId"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["gameId", "cheatId"]
    )
data class UGameCheatEntity(
    var gameId: Long,
    var cheatId: Long
)
