package com.adorablehappens.gamelibrary.navigation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.services.OptionsPrefsTheme
import com.adorablehappens.gamelibrary.services.OptionsVault
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel

object SCREENOptions  : RoutesScreens(
    route = "Options",
    icon = Icons.Filled.Menu,
    label = "Меню",
    contentDescription = "Меню",
) {
    @Composable
    override fun Content() {
        val vm: AppOverallViewModel = viewModel()
        val context = LocalContext.current
        val sharedPrefs = context.getSharedPreferences(OptionsVault.PREFS_FILENAME, Context.MODE_PRIVATE)
        val appOptions = vm.vmRepo.appOptions

        val appTheme = remember { appOptions.appTheme }

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
                    checked = appTheme.intValue != OptionsPrefsTheme.Light.code,
                    onCheckedChange = {
                        if (appTheme.intValue == OptionsPrefsTheme.Light.code) appTheme.intValue = OptionsPrefsTheme.Dark.code else appTheme.intValue = OptionsPrefsTheme.Light.code

                        appOptions.appTheme = appTheme
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
                        text = appOptions.randomGameTitle.value ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        )
                },
            )
        }
    }

}

