package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Класс-таблица(join-таблица) для связи GameEntity и CheatEntity по id
 *
 * @see WalkthroughEntity
 * @see WalkthroughImageEntity
 */
@Entity(tableName = "walkthrough_image_join",
    primaryKeys = ["walkthroughId", "walkthroughImageId"],
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = ["walkthroughId"],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = CheatEntity::class,
            parentColumns = ["id"],
            childColumns = ["walkthroughImageId"],
            onDelete = CASCADE
        )
    ],
    )
data class UWalkthroughWithImages(
    val walkthroughId: Long,
    val walkthroughImageId: Long
)
