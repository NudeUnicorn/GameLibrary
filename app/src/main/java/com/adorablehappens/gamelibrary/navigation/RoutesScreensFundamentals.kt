package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adorablehappens.gamelibrary.R
import com.adorablehappens.gamelibrary.viewmodels.AppOverallViewModel


/**
 * Основные элементы граф. интерфейса
 */

open class RoutesScreensFundamentals() {

    fun fd() {

    }

    object UI{
        @Composable
        fun ElementsCounter(
            size: Int = 0
        ){
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.listofentities_elements) + size,
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                    lineHeight = TextUnit(12f, TextUnitType.Sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        @Composable
        fun FrameRounded(
            modifier: Modifier = Modifier,
            backgrnd: Color = Color.LightGray,
            content: @Composable (() -> Unit) = {}
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    //.padding(20.dp, 20.dp, 20.dp)
                    //.height(200.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(20.dp))
                    .background(backgrnd)
            )
            Box(modifier = mod) {
                content()
            }
        }

        @Composable
        fun FrameRect(modifier: Modifier = Modifier, content: @Composable (() -> Unit) = {}) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    //.padding(20.dp, 20.dp, 20.dp, 20.dp)

                    //.clip(shape = RoundedCornerShape(20.dp))
                    //.border(2.dp,Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                //.padding(10.dp)
            )
            Box(modifier = mod) {
                content()
            }
        }

        @Composable
        fun BottomMenu(
            modifier: Modifier = Modifier,
            navController: NavController,
            vm: AppOverallViewModel,
            content: @Composable (() -> Unit) = {}
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxSize()
            )
            val dropdownMenuExpanded = remember { mutableStateOf(false) }
            var barItemSelected by remember { vm.navBarItemSelected }
            NavigationBar(
                Modifier,

            ) {
                RoutesMain.entries.forEachIndexed { index, entry ->
                    NavigationBarItem(
                        selected = entry == barItemSelected,
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                entry.route.icon,
                                contentDescription = entry.route.contentDescription
                            )
                        },
                        label = {
                            Text(
                                text = entry.route.label,
                                //fontSize = TextUnit(10f, TextUnitType.Sp),
                                textAlign = TextAlign.Center,
                                lineHeight = TextUnit(8f, TextUnitType.Sp),
                                overflow = TextOverflow.Ellipsis,
                                autoSize = TextAutoSize.StepBased(8.sp, 11.sp),
                            )
                        },
                        onClick = {
                            navController.navigate(entry.route.route)
                            barItemSelected = entry
                        },
                    )
                }
//                NavigationBarItem(
//                    selected = barItemSelected,
//                    icon = {Icon(RoutesMain.home.route.icon, "Главный экран")},
//                    label = {
//                        if (barItemSelected)
//                            Text(text = "Главный",
//                                maxLines = 2,
//                                overflow = TextOverflow.Ellipsis)
//                        else {}
//                            },
//                    onClick = {
//                        navController.navigate(RoutesMain.home.route.route)
//                    },
//                )
//                NavigationBarItem(
//                    selected = false,
//                    icon = {Icon(RoutesMain.favorites.route.icon, "Список избранного")},
//                    label = {Text(text = "Избранное")},
//                    onClick = {
//                        navController.navigate(RoutesMain.favorites.route.route)
//                    },
//                )
//                NavigationBarItem(
//                    selected = false,
//                    icon = {Icon(RoutesMain.randomise.route.icon, "Случайный список")},
//                    label = {Text(text = "Случайное")},
//                    onClick = {
//                        navController.navigate(RoutesMain.randomise.route.route)
//                    },
//                )
//                NavigationBarItem(
//                    selected = false,
//                    icon = {Icon(RoutesMain.stats.route.icon, "Стоимость всего")},
//                    label = {Text(text = "Стоимость")},
//                    onClick = {
//                        navController.navigate(RoutesMain.stats.route.route)
//                    },
//                )
//                NavigationBarItem(
//                    selected = false,
//                    icon = {Icon(RoutesMain.options.route.icon, "Меню")},
//                    label = {Text(text = "Меню")},
//                    onClick = {
//                        navController.navigate(RoutesService.createUpdateGame.route.route)
//                    },
//                )
            }
        }

        @Composable
        fun BottomMenuBtn(
            modifier: Modifier = Modifier,
            btnIcon: ImageVector = Icons.Filled.Home,
            btnIconTint: Color = Color.DarkGray,
            btnOnClick: () -> Unit = {},
            btnDescription: String = "",
            content: @Composable (() -> Unit) = {}
        ) {

            val modBox: Modifier = modifier.then(
                Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(20.dp))
                //.border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                //.background(Color.hsl(0f, 0f, 0.7f))
                //.padding(0.dp)
            )

            Box(
                modBox
                    .clickable(onClick = {
                        btnOnClick()
                    }),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.6f),
                    imageVector = btnIcon, contentDescription = btnDescription,
                    tint = btnIconTint
                )
                content()
            }

        }

        @Composable
        fun BottomDropdownMenu(
            modifier: Modifier = Modifier,
            navController: NavController,
            expanded: MutableState<Boolean> = remember { mutableStateOf(false) },
            content: @Composable (() -> Unit) = {}
        ) {

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Создать/Изменить") },
                    onClick = {
                        expanded.value = false
                        navController.navigate(RoutesService.createUpdateEX.route.route)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Второй пункт") },
                    onClick = {
                        expanded.value = false
                    }
                )
                HorizontalDivider()
                DropdownMenuItem(
                    text = { Text("Настройки") },
                    onClick = {
                        expanded.value = false
                    }
                )
            }
        }


        @Composable
        fun ToggleableButton(
            modifier: Modifier = Modifier,
            text: String = "",
            backgrnd: Color = Color.LightGray,
            backgrndTggl: Color = Color.DarkGray,

            ) {
            var btnState by remember { mutableStateOf(false) }
            val mod: Modifier = modifier.then(
                Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .border(
                        2.dp,
                        if (btnState) Color.Blue else Color.Gray,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(backgrnd)
                    .toggleable(
                        value = false,
                        interactionSource = null,
                        indication = LocalIndication.current,
                        enabled = true,
                        role = null,
                        onValueChange = { value ->
                            btnState = !btnState
                        }
                    )
                    .padding(10.dp, 5.dp)

            )

            Text(
                text = text,
                modifier = mod

            )

        }

        @Composable
        fun H1Text(
            modifier: Modifier = Modifier,
            text: String = "",
            fontSize: TextUnit = TextUnit(32f, TextUnitType.Sp),
            fontWeight: FontWeight = FontWeight.W900,

            ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
            )

            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )

        }

        @Composable
        fun H2Text(
            modifier: Modifier = Modifier,
            text: String = "",
            fontSize: TextUnit = TextUnit(26f, TextUnitType.Sp),
            fontWeight: FontWeight = FontWeight.W700,
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
            )

            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )

        }

        @Composable
        fun H3Text(
            modifier: Modifier = Modifier,
            text: String = "",
            fontSize: TextUnit = TextUnit(22f, TextUnitType.Sp),
            fontWeight: FontWeight = FontWeight.W500,
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
            )

            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )

        }

        @Composable
        fun SpacerVerticalFill(
            modifier: Modifier = Modifier,
            height: Int = 20
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(height.dp)
            )

            Spacer(mod)

        }

        @Composable
        fun SpacerHorizontalFill(
            modifier: Modifier = Modifier,
            width: Int = 5
        ) {
            val mod: Modifier = modifier.then(
                Modifier
                    .fillMaxHeight()
                    .width(width.dp)
            )

            Spacer(mod)

        }
    }

    companion object {

    }

}