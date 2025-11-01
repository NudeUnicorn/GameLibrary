package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

/**
 * Класс-таблица(join-таблица) для связи WalkthroughEntity и WalkthroughImageEntity по id
 *
 * @see WalkthroughEntity
 * @see WalkthroughImageEntity
 */
@Entity(tableName = "walkthrough_image_join",
    primaryKeys = ["walkthroughId", "walkthroughImageId"],
    foreignKeys = [
        ForeignKey(
            entity = WalkthroughEntity::class,
            parentColumns = ["id"],
            childColumns = ["walkthroughId"],
            onDelete = CASCADE
    ),
        ForeignKey(
            entity = WalkthroughImageEntity::class,
            parentColumns = ["id"],
            childColumns = ["walkthroughImageId"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("walkthroughId"), Index("walkthroughImageId"),]
    )
data class UWalkthroughWithImages(
    val walkthroughId: Long,
    val walkthroughImageId: Long
)
