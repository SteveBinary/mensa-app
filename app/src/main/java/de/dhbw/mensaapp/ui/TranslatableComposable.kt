package de.dhbw.mensaapp.ui

import androidx.compose.runtime.Composable


interface TranslatableComposable {

    @Composable
    fun translatedString(): String
}
