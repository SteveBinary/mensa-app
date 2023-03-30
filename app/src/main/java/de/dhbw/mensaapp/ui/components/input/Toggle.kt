package de.dhbw.mensaapp.ui.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle


@Composable
fun Toggle(checked: Boolean, text: String, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(0.8F),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle.Default.copy(
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Switch(checked = checked, onCheckedChange = onChange)
    }
}
