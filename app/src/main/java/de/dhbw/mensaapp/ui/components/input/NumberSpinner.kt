package de.dhbw.mensaapp.ui.components.input

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
import java.lang.Integer.max
import java.lang.Integer.min


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NumberSpinner(value: Int, minValue: Int, maxValue: Int, onChange: (Int) -> Unit) {
    AnimatedVisibility(
        visible = value <= minValue,
        enter = scaleIn(animationSpec = spring(dampingRatio = 0.2F)),
        exit = scaleOut()
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            IconButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = { onChange(constrain(value + 1, from = minValue, to = maxValue)) }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.AddShoppingCart,
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    contentDescription = stringResource(R.string.increment_number)
                )
            }
        }
    }

    AnimatedVisibility(
        visible = value > minValue,
        enter = scaleIn(animationSpec = tween(500)),
        exit = scaleOut(animationSpec = tween(0))
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedChangingText(value)

                Row(horizontalArrangement = Arrangement.SpaceEvenly) {

                    AnimatedVisibility(value > minValue) {
                        IconButton(onClick = { onChange(constrain(value - 1, from = minValue, to = maxValue)) }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.RemoveCircle,
                                tint = MaterialTheme.colorScheme.surfaceTint,
                                contentDescription = stringResource(R.string.decrement_number)
                            )
                        }
                    }

                    AnimatedVisibility(value < maxValue) {
                        IconButton(onClick = { onChange(constrain(value + 1, from = minValue, to = maxValue)) }) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.AddCircle,
                                tint = MaterialTheme.colorScheme.surfaceTint,
                                contentDescription = stringResource(R.string.increment_number)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun constrain(value: Int, from: Int, to: Int): Int {
    return max(from, min(value, to))
}

@Preview
@Composable
fun Preview() {
    NumberSpinner(0, -20, 20) {}
}
