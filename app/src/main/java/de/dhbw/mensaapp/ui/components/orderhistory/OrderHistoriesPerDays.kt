package de.dhbw.mensaapp.ui.components.orderhistory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.model.orderhistory.OrderHistoriesPerDay
import de.dhbw.mensaapp.ui.components.menu.Day


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderHistoriesPerDays(orderHistoriesPerDay: OrderHistoriesPerDay) {
    val lazyListState = rememberLazyListState()

    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        orderHistoriesPerDay.forEach { (day, orderHistories) ->

            stickyHeader {
                Day(day)
            }

            itemsIndexed(orderHistories) { innerIndex, orderHistory ->
                val topPadding = if (innerIndex <= 0) 15.dp else 10.dp
                val bottomPadding = if (innerIndex >= orderHistories.size - 1) 15.dp else 10.dp

                Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = topPadding, bottom = bottomPadding)) {

                    OrderHistory(orderHistory = orderHistory)
                }
            }
        }
    }
}
