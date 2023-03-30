package de.dhbw.mensaapp.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@Composable
fun Header(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .height(70.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .zIndex(10F),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            content()
        }
    }
}
