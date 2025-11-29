package com.adorablehappens.gamelibrary.services

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import com.adorablehappens.gamelibrary.navigation.OptionsPrefs

/**
 * Глобальное хранилище настроек приложения
 */
abstract class OptionsVault(
    setUpPrefs: () -> SharedPreferences
)
{
    protected val sharedPrefs: SharedPreferences = setUpPrefs()

    val appTheme = mutableStateOf(sharedPrefs.getInt(OptionsPrefs.theme.key, 0))


}