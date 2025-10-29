package com.adorablehappens.gamelibrary.dblogic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_id")
    var id: Int = 0,
    @ColumnInfo(name = "game_name")
    var name: String = "",
    @ColumnInfo(name = "game_subname")
    var subname: String = "",
    @ColumnInfo(name = "game_wordStoryShort")
    var wordStoryShort: String = "",
    @ColumnInfo(name = "game_image")    //главное изображение
    var image: String = "",
    @ColumnInfo(name = "game_image_icon")   //изображение-иконка, если не задано используется часть главного
    var imageIcon: String = "",
//    @ColumnInfo(name = "game_devs")
//    var devs: String = "",
//    @ColumnInfo(name = "game_devs_site")
//    var devsSite: String = "",
    @ColumnInfo(name = "game_startPlaying")
    //по нажатию на кнопку отмечается начало прохождения в целом или части игры, если поле уже задано
    var startPlaying: Long = 0,
    @ColumnInfo(name = "game_stopPlaying")
    //окончание прохождения в целом или части
    var stopPlaying: Long = 0,
    @ColumnInfo(name = "game_overallPlaying")
    //фактическое время в игре, считается как сумма разностей промежутков в игре
    var overallPlaying: Long = 0,
    @ColumnInfo(name = "game_description")
    var description: String = "",
    @ColumnInfo(name = "game_comment")
    var comment: String = "",
    @ColumnInfo(name = "game_timestamp")    //дата добавления в библиотеку
    var timestamp: Long = 0,
)
