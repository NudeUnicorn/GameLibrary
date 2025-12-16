package com.adorablehappens.gamelibrary.dblogic.behaviour

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoin
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoJoinBehaviour
import com.adorablehappens.gamelibrary.dblogic.entities.UWalkthroughWithImages
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOWalkthroughWithImages

object JOINWalkthroughWithImagesBEH{

    val obj = object : DBDaoJoinBehaviour<WalkthroughEntity, WalkthroughImageEntity, UWalkthroughWithImages, POJOWalkthroughWithImages>(){
        override fun setDAO(dao: DBDaoJoin<WalkthroughEntity, WalkthroughImageEntity, UWalkthroughWithImages, POJOWalkthroughWithImages>) {
            super.setDAO(dao)
        }

        override fun insertT(entity: WalkthroughEntity) {
            super.insertT(entity)
        }

        override fun insertY(entity: WalkthroughImageEntity) {
            super.insertY(entity)
        }

        override fun insertJoin(join: UWalkthroughWithImages) {
            super.insertJoin(join)
        }

        override fun getAllLinkedEntities(): LiveData<List<POJOWalkthroughWithImages>> {
            return super.getAllLinkedEntities()
        }

        override fun getOneLinkedEntity(id: Long): LiveData<POJOWalkthroughWithImages> {
            return super.getOneLinkedEntity(id)
        }

    }


}