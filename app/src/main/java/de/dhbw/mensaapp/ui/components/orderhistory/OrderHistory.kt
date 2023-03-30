package de.dhbw.mensaapp.ui.components.orderhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.model.orderhistory.OrderHistory
import de.dhbw.mensaapp.ui.components.input.InformationChip


@Composable
fun OrderHistory(orderHistory: OrderHistory) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .shadow(
                elevation = 3.dp,
                spotColor = MaterialTheme.colorScheme.onBackground,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(16.dp)
            )
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                InformationChip(text = orderHistory.timeSlot.formattedString, modifier = Modifier.width(60.dp))

                InformationChip(text = "${orderHistory.orderCount} ×", modifier = Modifier.width(60.dp))
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {

                Text(text = orderHistory.menuName, style = MaterialTheme.typography.titleMedium.copy(hyphens = Hyphens.Auto), color = MaterialTheme.colorScheme.onBackground)

                if (orderHistory.menuSubtitle.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = orderHistory.menuSubtitle, style = MaterialTheme.typography.titleSmall.copy(hyphens = Hyphens.Auto), color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}
