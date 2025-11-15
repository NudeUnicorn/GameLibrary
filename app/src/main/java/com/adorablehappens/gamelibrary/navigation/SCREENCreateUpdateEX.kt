package com.adorablehappens.gamelibrary.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SCREENCreateUpdateEX : RoutesScreens(
    route = "CreateUpdateEX",
) {

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        val scope = rememberCoroutineScope()
        val pages = listOf<PagerPage>(
            PagerPage(
                name = "Tags",
                content = {}
            )
        )
        val pageCurrent = remember { mutableStateOf(0) }
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { PagerPages.entries.size})

        Column(
            Modifier.padding(innerPadding)
        ) {

            TopMenu(pagerState = pagerState, coroutineScope = scope) {  }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) {page->

                when(PagerPages.entries[page].ordinal){
                    PagerPages.Tags.ordinal -> {
                        CreateUpdateTags()
                    }
                    PagerPages.Genres.ordinal -> {
                        CreateUpdateGenres()
                    }
                    PagerPages.Devs.ordinal -> {
                        CreateUpdateDevs()
                    }
                    PagerPages.GameEngines.ordinal -> {
                        CreateUpdateGameEngines()
                    }
                }
            }
        }

    }

    @Composable
    private fun TopMenu(
        modifier: Modifier = Modifier,
        pagerState: PagerState,
        coroutineScope: CoroutineScope,
        content: @Composable (() -> Unit) = {}
    ) {
        val scroll = ScrollState(0)
        val mod = modifier.then(Modifier
            .fillMaxWidth()
            .horizontalScroll(scroll))

        Row(mod) {
            PagerPages.entries.forEach { it->
                Button(onClick = {
                    coroutineScope.launch{

                        pagerState.animateScrollToPage(it.ordinal)
                        scroll.animateScrollTo(it.ordinal)
                    }
                },
                    modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = it.title)
                }
            }
            content()
        }

    }

    @Composable
    private fun CreateUpdateTags(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        Text(text = "Its Tags page, baby!")
    }
    @Composable
    private fun CreateUpdateGenres(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        Text(text = "Its Genres page, baby!")
    }
    @Composable
    private fun CreateUpdateDevs(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        Text(text = "Its Devs page, baby!")
    }
    @Composable
    private fun CreateUpdateGameEngines(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues = PaddingValues(0.dp),
        content: @Composable (() -> Unit) = {}
    ) {
        Text(text = "Its GameEngines page, baby!")
    }


    enum class PagerPages(
        val title: String,
        val content: @Composable ((
            modifier: Modifier,
                ) -> Unit) = {}
    ){
        Tags("Tags",{CreateUpdateTags()}),
        Genres("Genres",{CreateUpdateGenres()}),
        Devs("Devs",{CreateUpdateDevs()}),
        GameEngines("GameEngines",{CreateUpdateGameEngines()}),
    }

    data class PagerPage(
        val name: String,
        val content: @Composable (() -> Unit) = {}
    )
}
