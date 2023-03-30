package de.dhbw.mensaapp.ui.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(tabs: List<NavigationItem>) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { Navigation(navigationItems = tabs, navController = navController) },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background
    ) {

        Box(Modifier.padding(it)) {

            NavHost(navController = navController, startDestination = tabs[0].navRoute) {

                tabs.forEach { navigationItem ->

                    navigation(startDestination = navigationItem.navRoute + "Page", route = navigationItem.navRoute) {

                        composable(navigationItem.navRoute + "Page") {

                            navigationItem.content()
                        }
                    }
                }
            }
        }
    }
}
