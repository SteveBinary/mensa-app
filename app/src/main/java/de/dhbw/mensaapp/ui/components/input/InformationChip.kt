package de.dhbw.mensaapp.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun InformationChip(text: String, modifier: Modifier = Modifier, enabled: Boolean = false, highlightColor: Boolean = false, onClick: () -> Unit = {}) {
    SuggestionChip(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = SuggestionChipDefaults.suggestionChipColors(
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledLabelColor = if (highlightColor) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground,
            labelColor = if (highlightColor) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground
        ),
        label = {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-5).dp),
                textAlign = TextAlign.Center
            )
        }
    )
}
