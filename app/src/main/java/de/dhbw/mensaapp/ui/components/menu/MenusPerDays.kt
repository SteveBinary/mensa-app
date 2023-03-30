package de.dhbw.mensaapp.ui.components.menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay
import de.dhbw.mensaapp.model.menu.Day
import de.dhbw.mensaapp.model.menu.Menu
import de.dhbw.mensaapp.model.menu.TimeSlot


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenusPerDays(
    chosenMenusPerDay: ChosenMenusPerDay,
    appSettings: AppSettings,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onUpdateOrderCount: (Day, Menu, Int) -> Unit,
    onUpdateTime: (Day, Menu, TimeSlot?) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {

        LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            chosenMenusPerDay.forEach { (day, menusPerDay) ->

                stickyHeader {
                    Day(day)
                }

                itemsIndexed(menusPerDay.map { it.key to it.value }) { innerIndex, (menu, orderOptions) ->
                    val topPadding = if (innerIndex <= 0) 15.dp else 10.dp
                    val bottomPadding = if (innerIndex >= menusPerDay.size - 1) 15.dp else 10.dp

                    Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = topPadding, bottom = bottomPadding)) {

                        Menu(
                            menu = menu,
                            menuOrderOptions = orderOptions,
                            appSettings = appSettings,
                            onUpdateOrderCount = { newOrderCount ->
                                onUpdateOrderCount(day, menu, newOrderCount)
                            },
                            onUpdateTime = { newTime ->
                                onUpdateTime(day, menu, newTime)
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
