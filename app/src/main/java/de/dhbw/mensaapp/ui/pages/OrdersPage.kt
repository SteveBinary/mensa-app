package de.dhbw.mensaapp.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.orderhistory.OrderHistory
import de.dhbw.mensaapp.model.orderhistory.sortedOrderHistoriesPerDayBeforeOffset
import de.dhbw.mensaapp.ui.components.orderhistory.OrderHistoriesPerDays
import kotlin.time.Duration.Companion.hours


@Composable
fun OrdersPage(orderHistories: List<OrderHistory>, appSettings: AppSettings) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Header {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = stringResource(R.string.orders),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = stringResource(R.string.orders),
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        OrderHistoriesPerDays(
            orderHistoriesPerDay = orderHistories
                .sortedOrderHistoriesPerDayBeforeOffset(
                    includePastOrders = appSettings.showPastOrders,
                    offset = 1.hours
                )
        )
    }
}
