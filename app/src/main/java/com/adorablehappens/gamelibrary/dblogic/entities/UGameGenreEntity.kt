package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и GenreEntity по id
 *
 * @see GameEntity
 * @see GenreEntity
 */
@Entity(tableName = "game_genre_join",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["gameId", "genreId"]
)
data class UGameGenreEntity(
    val gameId: Long,
    val genreId: Long
)
