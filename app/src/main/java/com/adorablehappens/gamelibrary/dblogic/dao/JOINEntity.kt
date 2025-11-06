package com.adorablehappens.gamelibrary.dblogic.dao

/**
 *
 */
abstract class JOINEntity (

    open val parentEntityID: Long,
    open val childEntityID: Long

){
    companion object{

        const val PARENTIDNAME: String = "parentEntityID"
        const val CHILDIDNAME: String = "childEntityID"



    }
}

