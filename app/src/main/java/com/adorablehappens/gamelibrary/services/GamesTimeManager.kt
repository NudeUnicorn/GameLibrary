package com.adorablehappens.gamelibrary.services

import android.icu.util.Calendar
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Управляет подсчётом, сохранением и форматированием времени игры
 */
object GamesTimeManager {
    private val calendar = Calendar.getInstance()
    private val playtime = MutableStateFlow(mutableMapOf<Long, Long>())

    private val _stateInitial = MutableStateFlow<String>("")
    val stateInitial: StateFlow<String> = _stateInitial
    private val _stateStartPlaying = MutableStateFlow<String>("")
    val stateStartPlaying: StateFlow<String> = _stateStartPlaying
    private val _stateStopPlaying = MutableStateFlow<String>("")
    val stateStopPlaying: StateFlow<String> = _stateStopPlaying
    private val _stateOverallPlayed = MutableStateFlow<String>("")
    val stateOverallPlayed: StateFlow<String> = _stateOverallPlayed

    fun startPlaying(entity: GameEntity){
        if (entity.startPlaying == 0.toLong()){
            Repository.behGameRepo.update(
                GameEntity(
                    id = entity.id,
                    name = entity.name,
                    subname = entity.subname,
                    wordStoryShort = entity.wordStoryShort,
                    image = entity.image,
                    imageIcon = entity.imageIcon,
                    favorite = entity.favorite,
                    price = entity.price,
                    startPlaying = System.currentTimeMillis(),
                    stopPlaying = entity.stopPlaying,
                    overallPlaying = entity.overallPlaying,
                    description = entity.description,
                    comment = entity.comment,
                    timestamp = entity.timestamp
                )
            )
        }
        playtime.value.put(entity.id, System.currentTimeMillis())
    }

    /**
     * Функция внутри выполняется в фоне, в корутине репозитория.
     * Подсчитывает общее количество сыгранного времени,
     * устанавливает дату конечного запуска и обновляет данные в базе данных
     */
    fun stopPlayingS(entity: GameEntity){
        Repository.execCoroutine {
            stopPlaying(entity)
        }
    }
    fun stopPlaying(entity: GameEntity){
        val stopPlayingTime = System.currentTimeMillis()
        val overallPlaying: Long = stopPlayingTime - playtime.value[entity.id]!!

        entity.stopPlaying = stopPlayingTime
        entity.overallPlaying += overallPlaying

        Repository.behGameRepo.update(entity)
    }

    /**
     * Функция внутри выполняется в фоне, в корутине репозитория.
     * Возвращает строку для общего времени игры
     */
    fun overallPlayingS(time: Long): StateFlow<String> {
        Repository.execCoroutine {
            _stateOverallPlayed.value = getFormatTime(time)
        }
        return stateOverallPlayed
    }
    fun overallPlaying(time: Long): String {
        if (time == 0.toLong()){
            return "не запускали"
        }
        else{
            calendar.timeInMillis = time
            return (
                    calendar.get(java.util.Calendar.HOUR).toString() + ":" +
                            calendar.get(java.util.Calendar.MINUTE).toString() + ":" +
                            calendar.get(java.util.Calendar.SECOND).toString()
                    )
        }
    }

    /**
     * Функция внутри выполняется в фоне, в корутине репозитория.
     * Возвращает состояние со строкой внутри для времени и даты,
     * переданных в миллисикундах, для определённого типа даты
     */
    fun getFormatTimeS(time: Long, timetype: GameTimeType = GameTimeType.Init): StateFlow<String> {
        var localM = MutableStateFlow<String>("")
        var localS: StateFlow<String> = localM

        when(timetype){
            GameTimeType.Init -> {
                localM = _stateInitial
                localS = stateInitial
            }
            GameTimeType.Start -> {
                localM = _stateStartPlaying
                localS = stateStartPlaying
            }
            GameTimeType.Stop -> {
                localM = _stateStopPlaying
                localS = stateStopPlaying
            }
            GameTimeType.Overall -> {
                localM = _stateOverallPlayed
                localS = stateOverallPlayed
            }
        }
        Repository.execCoroutine {
            localM.value = getFormatTime(time)
        }
        return localS
    }
    fun getFormatTime(time: Long): String{
        calendar.timeInMillis = time
        return if (time == 0.toLong()){
            "не запускали"
        } else{
            calendar.get(java.util.Calendar.HOUR).toString() + ":" +
                    calendar.get(java.util.Calendar.MINUTE).toString() + ":" +
                    calendar.get(java.util.Calendar.SECOND).toString() + " / " +
                    calendar.get(java.util.Calendar.DAY_OF_MONTH).toString() + "." +
                    calendar.get(java.util.Calendar.MONTH).toString() + "." +
                    calendar.get(java.util.Calendar.YEAR).toString()
        }
    }

}

enum class GameTimeType(){
    Start(),
    Stop(),
    Overall(),
    Init(),
}
