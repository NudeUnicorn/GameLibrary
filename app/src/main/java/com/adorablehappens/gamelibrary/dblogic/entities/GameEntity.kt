package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adorablehappens.gamelibrary.dblogic.dao.EntityBase

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long,
    @ColumnInfo(name = "name")
    override var name: String = "",
    @ColumnInfo(name = "subname")
    var subname: String = "",
    @ColumnInfo(name = "wordStoryShort")
    var wordStoryShort: String = "",
    @ColumnInfo(name = "image")    //главное изображение
    var image: String = "",
    @ColumnInfo(name = "image_icon")   //изображение-иконка, если не задано используется часть главного
    var imageIcon: String = "",
//    @ColumnInfo(name = "devs")
//    var devs: String = "",
//    @ColumnInfo(name = "devs_site")
//    var devsSite: String = "",
    //стоимость игры
//    @ColumnInfo(name = "price")
//    var price: Int = "",
    //валюта стоимости игры
//    @ColumnInfo(name = "price_currency")
//    var currency: Long = "",

    @ColumnInfo(name = "startPlaying")
    //по нажатию на кнопку отмечается начало прохождения в целом или части игры, если поле уже задано
    val startPlaying: Long,
    @ColumnInfo(name = "stopPlaying")
    //окончание прохождения в целом или части
    var stopPlaying: Long = 0,
    @ColumnInfo(name = "overallPlaying")
    //фактическое время в игре, считается как сумма разностей промежутков в игре
    var overallPlaying: Long = 0,
    @ColumnInfo(name = "description")
    override var description: String = "",
    @ColumnInfo(name = "comment")
    override var comment: String = "",
    @ColumnInfo(name = "timestamp")    //дата добавления в библиотеку
    val timestamp: Long,
): EntityBase(
    id = id,
    name = name,
    description = description,
    comment = comment)

