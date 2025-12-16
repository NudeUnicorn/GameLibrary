package com.adorablehappens.gamelibrary.services

import android.content.SharedPreferences
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

/**
 * Глобальное хранилище настроек приложения
 */
abstract class OptionsVault(
    setUpPrefs: (prefsName: String) -> SharedPreferences
)
{
    protected val sharedPrefs: SharedPreferences = setUpPrefs(PREFS_FILENAME)
    private val calendar = Calendar.getInstance()
    private val calendar1 = Calendar.getInstance()

    private val _appTheme =
        mutableIntStateOf(sharedPrefs.getInt(OptionsPrefs.Theme.key, 0))
    var appTheme
        get() = _appTheme
        set(value) = sharedPrefs.edit() {
            putInt(OptionsPrefs.Theme.key, value.intValue)
        }
    private val _randomGameTitle =
        mutableStateOf(sharedPrefs.getString(OptionsPrefs.RandomGameTitle.key, ""))
    var randomGameTitle
        get() = _randomGameTitle
        set(value) = sharedPrefs.edit() {
            putString(OptionsPrefs.RandomGameTitle.key, value.value)
        }
    private val _randomGameTime =
        mutableLongStateOf(sharedPrefs.getLong(OptionsPrefs.RandomGameTime.key, 0))
    var randomGameTime
        get() = _randomGameTime
        set(value) = sharedPrefs.edit() {
            putLong(OptionsPrefs.RandomGameTime.key, value.longValue)
        }
    private val strSet: Set<String>
        get() = sharedPrefs.getStringSet(OptionsPrefs.WishlistSet.key, emptySet<String>())!!.toSet()

    private val _wishlistSet
        get() = mutableStateOf(strSet)
    var wishlistSet
        get() = _wishlistSet
        set(value) = sharedPrefs.edit() {
            putStringSet(OptionsPrefs.WishlistSet.key, value.value)
        }
    private val _canChooseRandomGame = MutableStateFlow<Boolean>(canChooseRandom())
    val canChooseRandomGame: StateFlow<Boolean> = _canChooseRandomGame

    /**
     * Показывает можно ли использовать кнопку случайной игры.
     * Время до повторного использования 1 день.
     * Для реактивного обновления используйте свойство canChooseRandomGame
     */
    fun canChooseRandom(): Boolean{
        if (_randomGameTime.longValue != 0.toLong()){
            calendar.timeInMillis = System.currentTimeMillis()
            calendar1.timeInMillis = _randomGameTime.longValue
            calendar1.add(Calendar.DAY_OF_MONTH,1)
            if (calendar > calendar1){
                _randomGameTime.longValue = 0
                randomGameTime = _randomGameTime
                return true
            }
            else{
                return false
            }
        }
        else{
            println("canChooseRandom - true")
            return true
        }
    }

    /**
     * Устанавливает время нажатия кнопки случайной игры
     */
    fun setChooseRandomTime(){
        _randomGameTime.longValue = System.currentTimeMillis()
        randomGameTime = _randomGameTime
        _canChooseRandomGame.value = canChooseRandom()
    }


    companion object{
        const val PREFS_FILENAME: String = "app_prefs"
    }
}

enum class OptionsPrefs(
    val key: String,
)
{
    Theme("app_theme"),
    RandomGameTitle("app_random_game_title"),
    RandomGameTime("app_random_game_time"),
    WishlistSet("app_wishlist_set"),
}
enum class OptionsPrefsTheme(
    val key: String,
    val code: Int
)
{
    Light("theme_light", 0),
    Dark("theme_dark", 1)
}