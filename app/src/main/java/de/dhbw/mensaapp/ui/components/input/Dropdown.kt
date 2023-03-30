package de.dhbw.mensaapp.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import de.dhbw.mensaapp.ui.TranslatableComposable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : TranslatableComposable> Dropdown(currentItem: T, values: Iterable<T>, label: @Composable () -> Unit, onChange: (T) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = currentItem.translatedString(),
            label = label,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            values.forEach { item ->

                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = item.translatedString()) },
                    onClick = {
                        expanded = false
                        onChange(item)
                    }
                )
            }
        }
    }
}
