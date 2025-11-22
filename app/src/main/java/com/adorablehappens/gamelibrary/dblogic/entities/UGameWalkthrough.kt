package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntityBase

/**
 * Класс-таблица(join-таблица) для связи GameEntity и WalkthroughEntity по id
 *
 * @see GameEntity
 * @see WalkthroughEntity
 */
@Entity(tableName = "game_walkthrough_join",
    primaryKeys = [JOINEntity.Companion.PARENTIDNAME, JOINEntity.Companion.CHILDIDNAME],
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = [JOINEntity.Companion.PARENTIDNAME],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = WalkthroughEntity::class,
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
data class UGameWalkthrough(
    override val parentEntityID: Long,
    override val childEntityID: Long
): JOINEntityBase