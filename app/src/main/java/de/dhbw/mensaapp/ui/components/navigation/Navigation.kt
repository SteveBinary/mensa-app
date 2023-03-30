package de.dhbw.mensaapp.ui.components.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions


@Composable
fun Navigation(navigationItems: List<NavigationItem>, navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val parentRouteName = navBackStackEntry.value?.destination?.parent?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .height(60.dp)
            .shadow(
                elevation = 20.dp,
                spotColor = MaterialTheme.colorScheme.onBackground,
                ambientColor = MaterialTheme.colorScheme.onBackground
            )
    ) {
        navigationItems.forEach { navigationItem ->
            NavigationBarItem(
                selected = parentRouteName == navigationItem.navRoute,
                icon = { Icon(imageVector = navigationItem.icon, contentDescription = stringResource(navigationItem.name)) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedIconColor = MaterialTheme.colorScheme.tertiaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.onBackground
                ),
                onClick = {
                    navController.navigate(navigationItem.navRoute, navOptions {
                        // avoid building up a large stack of destinations on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        // avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        restoreState = true
                    })
                }
            )
        }
    }
}
