package com.adorablehappens.gamelibrary.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel

object SCREENOptions  : RoutesScreens(
    route = "Options",
    icon = Icons.Filled.Menu,
    label = "Меню",
    contentDescription = "Меню",
) {
    const val PREFS_FILENAME: String = "app_prefs"
    @Composable
    override fun Content() {
        val vm: AppOverallViewModel = viewModel()
        val context = LocalContext.current
        val sharedPrefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val appOptions = vm.vmRepo.appOptions

        val appTheme = remember { appOptions.appTheme }


        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = "Switch app theme")
                Switch(
                    checked = appTheme.value != OptionsPrefsTheme.light.code,
                    onCheckedChange = {
                        if (appTheme.value == OptionsPrefsTheme.light.code) appTheme.value = OptionsPrefsTheme.dark.code else appTheme.value = OptionsPrefsTheme.light.code

                        sharedPrefs.edit() {
                            putInt(OptionsPrefs.theme.key, appTheme.value)
                        }
                    }
                )
            }
        }
    }

}

enum class OptionsPrefs(
    val key: String,
)
{
    theme("app_theme")
}
enum class OptionsPrefsTheme(
    val key: String,
    val code: Int
)
{
    light("theme_light", 0),
    dark("theme_dark", 1)
}