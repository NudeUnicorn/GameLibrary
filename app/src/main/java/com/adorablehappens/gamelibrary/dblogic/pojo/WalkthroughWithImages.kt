package com.adorablehappens.gamelibrary.dblogic.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UAuthorCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UGameCheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity

data class WalkthroughWithImages(
    @Embedded
    val walkthrough: WalkthroughEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UWalkthroughWithImages::class,
            parentColumn = "walkthroughId",
            entityColumn = "walkthroughImageId"
        )
    )
    val walkthroughImages: List<WalkthroughImageEntity>//изображения для каждого этапа прохождения(заметки)
)
