package de.dhbw.mensaapp.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction


@Composable
fun TextField(text: String, title: String, validation: (String) -> Boolean, onChange: (String) -> Unit) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = title) },
        isError = !validation(text),
        singleLine = true,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onSend = { focusManager.clearFocus() },
        )
    )
}
