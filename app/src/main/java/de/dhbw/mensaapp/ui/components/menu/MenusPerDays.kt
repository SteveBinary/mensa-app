package de.dhbw.mensaapp.ui.components.menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoMeals
import androidx.compose.material.icons.filled.SwipeDownAlt
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
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

            if (chosenMenusPerDay.isEmpty()) {
                item {
                    NoMenusToOrderMessage()
                }
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


@Composable
fun NoMenusToOrderMessage() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.NoMeals,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = R.string.no_menus_can_be_ordered),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.no_menus_can_be_ordered),
            style = MaterialTheme.typography.titleMedium.copy(hyphens = Hyphens.Auto),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(id = R.string.swipe_down_to_refresh),
            style = MaterialTheme.typography.titleMedium.copy(hyphens = Hyphens.Auto),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Filled.SwipeDownAlt,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = R.string.swipe_down_to_refresh),
        )
    }
}
