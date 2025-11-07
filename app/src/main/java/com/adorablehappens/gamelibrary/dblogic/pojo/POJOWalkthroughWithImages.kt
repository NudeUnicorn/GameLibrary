package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.dao.JOINEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity

data class POJOWalkthroughWithImages(
    @Embedded
    val walkthrough: WalkthroughEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UWalkthroughWithImages::class,
            parentColumn = JOINEntity.Companion.PARENTIDNAME,
            entityColumn = JOINEntity.Companion.CHILDIDNAME
        )
    )
    val walkthroughImages: List<WalkthroughImageEntity>//изображения для каждого этапа прохождения(заметки)
)
