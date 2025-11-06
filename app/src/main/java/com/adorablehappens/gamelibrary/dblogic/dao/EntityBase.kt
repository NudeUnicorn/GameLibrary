package com.adorablehappens.gamelibrary.dblogic.dao


abstract class EntityBase(
    open val id: Long,
    open var name: String = "",
    open var description: String = "",
    open var comment: String = "",
) {
}