package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOWalkthroughWithImages

@Dao
interface JOINWalkthroughWithImagesDAO
    : DBDaoJoin<WalkthroughEntity, WalkthroughImageEntity, UWalkthroughWithImages, POJOWalkthroughWithImages> {

    @Transaction
    @Query("SELECT * FROM walkthroughes")
    override fun getAllLinkedEntities(): LiveData<List<POJOWalkthroughWithImages>>

    @Transaction
    @Query("SELECT * FROM walkthroughes WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): LiveData<POJOWalkthroughWithImages>


}