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
@Entity(tableName = "game_cheat_join",
    primaryKeys = ["gameId", "cheatId"],
    foreignKeys = [
        ForeignKey(
        entity = GameEntity::class,
        parentColumns = ["gameId"],
        childColumns = ["gameId"],
        onDelete = CASCADE
    ),
        ForeignKey(
            entity = CheatEntity::class,
            parentColumns = ["cheatId"],
            childColumns = ["cheatId"],
            onDelete = CASCADE
        )
    ],
    )
data class UGameCheatEntity(
    val gameId: Long,
    val cheatId: Long
)
