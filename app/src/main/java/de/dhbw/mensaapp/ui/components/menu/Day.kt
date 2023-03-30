package de.dhbw.mensaapp.ui.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.model.menu.Day


@Composable
fun Day(day: Day) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = day.translatedName(),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = day.formattedString(),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
