package com.adorablehappens.gamelibrary.services

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * Глобальное хранилище настроек приложения
 */
abstract class OptionsVaultDataStore(
    setUpDataStorePrefs: (prefsName: String) -> DataStore<Preferences>
)
{
    protected val dataStorePrefs: DataStore<Preferences> = setUpDataStorePrefs(PREFS_FILENAME)
    private val scope = MainScope()
    private val calendar = Calendar.getInstance()
    private val calendar1 = Calendar.getInstance()

    val appTheme: Flow<Int> = dataStorePrefs.data.map { preferences ->
        preferences[APP_THEME_KEY] ?: 0
    }

    val randomGameTitle = dataStorePrefs.data.map { preferences ->
        preferences[APP_RANDOM_GAME_TITLE_KEY] ?: ""
    }

    val randomGameTime = dataStorePrefs.data.map { preferences ->
        preferences[APP_RANDOM_GAME_TIME_KEY] ?: 0
    }
    private var randomGameTime1: StateFlow<Long> = randomGameTime.stateIn(scope, SharingStarted.Lazily, 0)

    val wishlistSet = dataStorePrefs.data.map { preferences ->
        preferences[APP_WISHLIST_SET_KEY] ?: emptySet()
    }

    init {
//        scope.launch {
//            randomGameTime.collect { value ->
//                randomGameTime1 = MutableStateFlow<Long>(value).asStateFlow()
//            }
//        }
    }

    private val _canChooseRandomGame
        get() = MutableStateFlow<Boolean>(canChooseRandom())
    val canChooseRandomGame: StateFlow<Boolean> = _canChooseRandomGame

    /**
     * Показывает можно ли использовать кнопку случайной игры.
     * Время до повторного использования 1 день.
     * Для реактивного обновления используйте свойство canChooseRandomGame
     */
    fun canChooseRandom(): Boolean{
        if (randomGameTime1.value != 0.toLong()){
            calendar.timeInMillis = System.currentTimeMillis()
            calendar1.timeInMillis = randomGameTime1.value
            calendar1.add(Calendar.DAY_OF_MONTH,1)
            if (calendar > calendar1){
                saveChooseRandomTime(0)
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
    fun saveChooseRandomTime(time: Long? = null){
        val timeLocal:Long = time ?: System.currentTimeMillis()
        scope.launch(Dispatchers.IO) {
            dataStorePrefs.edit {preferences ->
                preferences[APP_RANDOM_GAME_TIME_KEY] = timeLocal
            }
        }
        //canChooseRandom()
    }
    fun saveAppTheme(themeID: Int){
        scope.launch(Dispatchers.IO) {
            dataStorePrefs.edit {preferences ->
                preferences[APP_THEME_KEY] = themeID
            }
        }
    }
    fun saveRandomGameTitle(title: String){
        scope.launch(Dispatchers.IO) {
            dataStorePrefs.edit {preferences ->
                preferences[APP_RANDOM_GAME_TITLE_KEY] = title
            }
        }
    }
    fun saveWishlist(strSet: Set<String>){
        scope.launch(Dispatchers.IO) {
            dataStorePrefs.edit {preferences ->
                preferences[APP_WISHLIST_SET_KEY] = strSet
            }
        }
    }


    companion object{
        const val PREFS_FILENAME: String = "app_prefs"
        val APP_THEME_KEY = intPreferencesKey("app_theme")
        val APP_RANDOM_GAME_TITLE_KEY = stringPreferencesKey("app_random_game_title")
        val APP_RANDOM_GAME_TIME_KEY = longPreferencesKey("app_random_game_time")
        val APP_WISHLIST_SET_KEY = stringSetPreferencesKey("app_wishlist_set")
    }
}

enum class OptionsPrefs1(
    val key: String,
)
{
    Theme("app_theme"),
    RandomGameTitle("app_random_game_title"),
    RandomGameTime("app_random_game_time"),
    WishlistSet("app_wishlist_set"),
}
enum class OptionsPrefsTheme1(
    val key: String,
    val code: Int
)
{
    Light("theme_light", 0),
    Dark("theme_dark", 1)
}