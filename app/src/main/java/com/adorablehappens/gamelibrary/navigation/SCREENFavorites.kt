package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateSet
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adorablehappens.gamelibrary.navigation.RoutesScreensFundamentals.UI.ElementsCounter
import com.adorablehappens.gamelibrary.viewmodels.LibraryViewModel

object SCREENFavorites  : RoutesScreens(
    route = "Favorites",
    icon = Icons.Filled.Favorite,
    label = "Избранное",
    contentDescription = "Список избранного",
) {
    @Composable
    override fun Content() {
        val vm: LibraryViewModel = viewModel()
        //val appOptions = vm.vmRepo.appOptions
        val appOptionsDS = vm.vmRepo.appOptionsDataStore
        val setStrDS = appOptionsDS.wishlistSet.collectAsState(emptySet())

        val isVisibleAddToWishlistTextField = remember { mutableStateOf(false) }
        val strSet = remember { derivedStateOf { mutableSetOf(*(setStrDS.value.toTypedArray())) } }

        println("Wishlist - " + setStrDS.value)
        Column {
            Wishlist(
                modifier = Modifier.weight(1f),
                onRemove = {element->
                        strSet.value.remove(element)
                        //appOptions.wishlistSet = mutableStateOf(strSet.toSet())
                        appOptionsDS.saveWishlist(strSet.value.toSet())
                },
                strSet = strSet.value,
            )
            WishlistControlPanel(
                modifier = Modifier.fillMaxWidth(),
                onConfirmation = {newSetItem ->
                    if (newSetItem.isNotBlank()){
                        strSet.value.add(newSetItem)
                        //appOptions.wishlistSet = mutableStateOf(strSet.toSet())
                        appOptionsDS.saveWishlist(strSet.value.toSet())
                    }
                },
                isVisibleAddField = isVisibleAddToWishlistTextField,
                size = strSet.value.size
            )
        }
    }

    @Composable
    fun Wishlist(
        modifier: Modifier = Modifier,
        onRemove: (item: String)-> Unit = {},
        strSet: MutableSet<String>,
    ){
        val mod = modifier.then(Modifier)
        if (strSet.isEmpty()) {
            Box(
                mod.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "It seems no games wishlisted yet...\n Its great time to add a first one!"
                )
            }

        } else {
            LazyColumn(mod) {
                items(strSet.size) { index ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = strSet.elementAt(index)
                            )
                        },
                        trailingContent = {
                            IconButton(
                                onClick = {
                                    onRemove(strSet.elementAt(index))
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.RemoveCircleOutline,
                                    "Remove game from wishlist"
                                )
                            }
                        }
                    )
                }

            }
        }
    }
    @Composable
    fun WishlistControlPanel(
        modifier: Modifier = Modifier,
        onConfirmation: (fieldText: String)->Unit = {},
        isVisibleAddField: MutableState<Boolean>,
        size: Int = 0
    ){
        val fieldState = rememberTextFieldState()
        val fieldColors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
        )
        Column(modifier) {
            if (isVisibleAddField.value){
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = fieldState,
                        placeholder = {
                            Text(
                                text = "Write a wonderfull title of game you want to wishlist"
                            )
                        },
                        colors = fieldColors
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (!isVisibleAddField.value){
                    Button(
                        onClick = {
                            isVisibleAddField.value = !isVisibleAddField.value
                        }
                    ) {
                        Text(
                            text = "+ Add to wishlist"
                        )
                    }
                }else{
                    Button(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            isVisibleAddField.value = !isVisibleAddField.value
                        }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }
                    Button(
                        onClick = {
                            onConfirmation(fieldState.text.toString())
                            isVisibleAddField.value = !isVisibleAddField.value
                        }
                    ) {
                        Text(
                            text = "Save"
                        )
                    }
                }
            }
            ElementsCounter(size)
        }
    }



}