package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и GameEngineEntity по id
 *
 * @see GameEntity
 * @see GameEngineEntity
 */
@Entity(tableName = "game_engine_join",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GameEngineEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameEngineId"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["gameId", "gameEngineId"]
)
data class UGameEngineEntity(
    val gameId: Long,
    val gameEngineId: Long
)
