package de.dhbw.mensaapp.ui.components.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


data class NavigationItem(
    val icon: ImageVector,
    @StringRes
    val name: Int,
    val navRoute: String,
    val content: @Composable () -> Unit
)
