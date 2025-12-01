package com.adorablehappens.gamelibrary.services

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import com.adorablehappens.gamelibrary.navigation.OptionsPrefs

/**
 * Глобальное хранилище настроек приложения
 */
abstract class OptionsVault(
    setUpPrefs: (prefsName: String) -> SharedPreferences
)
{
    protected val sharedPrefs: SharedPreferences = setUpPrefs(PREFS_FILENAME)

    val appTheme = mutableStateOf(sharedPrefs.getInt(OptionsPrefs.theme.key, 0))


    companion object{
        const val PREFS_FILENAME: String = "app_prefs"
    }
}