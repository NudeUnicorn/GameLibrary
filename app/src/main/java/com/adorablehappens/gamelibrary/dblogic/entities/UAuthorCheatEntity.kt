package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и CheatEntity по id
 *
 * @see AuthorEntity
 * @see CheatEntity
 */
@Entity(tableName = "author_cheat_join",
    primaryKeys = ["authorId", "cheatId"],
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = ["authorId"],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = CheatEntity::class,
            parentColumns = ["id"],
            childColumns = ["cheatId"],
            onDelete = CASCADE
        )
    ],
    )
data class UAuthorCheatEntity(
    val authorId: Long,
    val cheatId: Long
)
