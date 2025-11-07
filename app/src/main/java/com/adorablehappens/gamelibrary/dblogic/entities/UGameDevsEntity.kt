package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity

/**
 * Класс-таблица(join-таблица) для связи GameEntity и DevEntity по id
 *
 * @see GameEntity
 * @see DevEntity
 */
@Entity(tableName = "game_dev_join",
    primaryKeys = [JOINEntity.Companion.PARENTIDNAME, JOINEntity.Companion.CHILDIDNAME],
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = [JOINEntity.Companion.PARENTIDNAME],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = DevEntity::class,
            parentColumns = ["id"],
            childColumns = [JOINEntity.Companion.CHILDIDNAME],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(JOINEntity.Companion.PARENTIDNAME),
        Index(JOINEntity.Companion.CHILDIDNAME),
    ]
    )
data class UGameDevsEntity(
    val parentEntityID: Long,
    val childEntityID: Long
)