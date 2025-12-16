package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntityBase

/**
 * Класс-таблица(join-таблица) для связи GameEntity и CheatEntity по id
 *
 * @see AuthorEntity
 * @see CheatEntity
 */
@Entity(tableName = "author_cheat_join",
    primaryKeys = [JOINEntity.Companion.PARENTIDNAME, JOINEntity.Companion.CHILDIDNAME],
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = [JOINEntity.Companion.CHILDIDNAME],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = CheatEntity::class,
            parentColumns = ["id"],
            childColumns = [JOINEntity.Companion.PARENTIDNAME],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(JOINEntity.Companion.PARENTIDNAME),
        Index(JOINEntity.Companion.CHILDIDNAME),
    ]
    )
data class UCheatAuthorEntity(
    override val parentEntityID: Long,
    override val childEntityID: Long
): JOINEntityBase