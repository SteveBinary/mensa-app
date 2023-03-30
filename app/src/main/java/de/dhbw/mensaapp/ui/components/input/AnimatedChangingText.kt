package de.dhbw.mensaapp.ui.components.input

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T : Comparable<T>> AnimatedChangingText(value: T) {
    var oldValue by rememberSaveable { mutableStateOf(value) }

    SideEffect {
        oldValue = value
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        val valueString = value.toString()
        val oldValueString = oldValue.toString()

        for (i in valueString.indices) {
            val newChar = valueString[i]
            val oldChar = oldValueString.getOrNull(i)

            val charToAnimate = if (oldChar == newChar) {
                oldValueString[i]
            } else {
                valueString[i]
            }

            AnimatedContent(
                targetState = charToAnimate,
                transitionSpec = {
                    if (oldValue <= value) {
                        slideInVertically { it } with slideOutVertically { -it }
                    } else {
                        slideInVertically { -it } with slideOutVertically { it }
                    }
                }
            ) { char ->

                Text(
                    text = char.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    softWrap = false
                )
            }
        }
    }
}
