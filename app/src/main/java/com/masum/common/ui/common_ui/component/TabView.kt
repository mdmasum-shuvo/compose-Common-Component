package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.masum.common.ui.theme.*

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowLayout(pagerState: PagerState, tabList: List<String>) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.background(white_color),
        backgroundColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                height = 1.dp,
                color = Color.Transparent
            )
        }
    ) {
        tabList.forEachIndexed { index, _ ->
            Column {
                Tab(
                    text = {
                        TextView15_W500(
                            value = tabList[index],
                            color = if (pagerState.currentPage == index) Color.Transparent else text_color,
                            textAlign = TextAlign.Center,
                            style = TextStyle(brush = if (pagerState.currentPage == index) gradientColor() else null)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
                if (pagerState.currentPage == index)
                    HorizontalDivider(modifier = Modifier.background(gradientColor()), thickness = 2.dp, color = Color.Transparent)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, tabContent: List<@Composable (() -> Unit)>) {

    /*val contentList: MutableList<@Composable (() -> Unit)> = mutableListOf({ ScheduleVisitScreen() },{ TextView10_W400(value = "test2") })*/

    HorizontalPager(state = pagerState) { page ->
        tabContent[page].invoke()
    }
}

/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TabContentScreen()
            1 -> TabContentScreen()
        }
    }
}*/
