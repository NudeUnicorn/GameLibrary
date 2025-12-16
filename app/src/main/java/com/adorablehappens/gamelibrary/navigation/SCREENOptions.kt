package com.adorablehappens.gamelibrary.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.services.OptionsPrefsTheme
import com.adorablehappens.gamelibrary.services.OptionsVault
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel

object SCREENOptions  : RoutesScreens() {
    override val route: String = "Options"
    override val icon: ImageVector = Icons.Filled.Menu
    override val label: String = "Меню"
    override val contentDescription: String = "Меню"

    @Composable
    override fun Content() {
        val vm: AppOverallViewModel = viewModel()
        val context = LocalContext.current
        val sharedPrefs = context.getSharedPreferences(OptionsVault.PREFS_FILENAME, Context.MODE_PRIVATE)
        val appOptions = vm.vmRepo.appOptions
        val appOptionsDS = vm.vmRepo.appOptionsDataStore

        //val appTheme = remember { appOptions.appTheme }
        var appThemeInt by remember { mutableIntStateOf(0) }
        val appThemeDS = appOptionsDS.appTheme.collectAsState(0)
        val randomGameTitleDS = appOptionsDS.randomGameTitle.collectAsState("")

        val outerPadding = 16.dp

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(outerPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(text = stringResource(R.string.options_apptheme))
                Switch(
                    checked = appThemeDS.value != OptionsPrefsTheme.Light.code,
                    onCheckedChange = {
                        appThemeInt =
                            if (appThemeDS.value == OptionsPrefsTheme.Light.code) OptionsPrefsTheme.Dark.code else OptionsPrefsTheme.Light.code

                        appOptionsDS.saveAppTheme(appThemeInt)
                    }
                )
            }

            ListItem(
                modifier = Modifier,
                headlineContent = {
                    Text(text = stringResource(R.string.options_random_game))
                },
                overlineContent = {

                },
                supportingContent = {
                    Text(text = stringResource(R.string.options_random_game_nextday_tip))
                },
                trailingContent = {
                    Text(
                        text = randomGameTitleDS.value,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                },
            )
        }
    }

}

