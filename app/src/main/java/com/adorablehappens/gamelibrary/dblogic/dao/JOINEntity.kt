package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 */
@Entity
abstract class JOINEntity (

    @PrimaryKey(autoGenerate = true)
    open val id: Long,
    open val parentEntityID: Long,
    open val childEntityID: Long

){
    companion object{

        const val PARENTIDNAME: String = "parentEntityID"
        const val CHILDIDNAME: String = "childEntityID"



    }
}

