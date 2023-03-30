package de.dhbw.mensaapp.ui.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.api.MenuRepositoryManager
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.isValidForOrderingMenus
import de.dhbw.mensaapp.model.menu.*
import de.dhbw.mensaapp.ui.components.menu.MenusPerDays
import de.dhbw.mensaapp.ui.components.menu.OrderPopup
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MenuPage(
    appSettings: AppSettings,
    menuRepositoryManager: MenuRepositoryManager,
    context: Context,
    onSuccessfulOrder: (ChosenMenusPerDay) -> Unit
) {
    var chosenMenusPerDay by rememberSaveable { mutableStateOf<ChosenMenusPerDay>(emptyMap()) }
    var firstStart by rememberSaveable { mutableStateOf(true) }
    var refreshing by rememberSaveable { mutableStateOf(false) }
    var showOrderPopup by rememberSaveable { mutableStateOf(false) }
    var orderingInProgress by rememberSaveable { mutableStateOf(false) }
    var orderingWasSuccessful by rememberSaveable { mutableStateOf(false) }
    var orderingCompleted by rememberSaveable { mutableStateOf(false) }

    if (firstStart) {
        firstStart = false
        menuRepositoryManager.getMenusPerDays(
            onFetchingData = { refreshing = true },
            onNotConnectedToNetwork = {
                refreshing = false
                Toast.makeText(context, R.string.not_connected_to_network, Toast.LENGTH_LONG).show()
            },
            onErrorFetchingMenus = {
                refreshing = false
                Toast.makeText(context, R.string.error_fetching_menus, Toast.LENGTH_LONG).show()
            },
            onData = {
                refreshing = false
                chosenMenusPerDay = it
            }
        )
    }

    val filteredOrders by remember {
        derivedStateOf {
            val now = LocalDateTime.now()
            chosenMenusPerDay.filterKeys { day ->
                val menuDayAt9oClock = LocalDateTime.of(day.date, LocalTime.of(9, 0, 0))
                now < menuDayAt9oClock
            }
        }
    }

    val backgroundBlur by animateDpAsState(
        targetValue = if (showOrderPopup) 20.dp else 0.dp,
        animationSpec = snap()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .blur(backgroundBlur),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Filled.Restaurant,
                    contentDescription = stringResource(R.string.refresh_menus),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = stringResource(R.string.app_name),
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Button(onClick = { showOrderPopup = true }) {

                AnimatedContent(
                    targetState = chosenMenusPerDay,
                    transitionSpec = {
                        if (targetState.totalNumberOfOrderedMenus() == 0) {
                            scaleIn(animationSpec = spring(dampingRatio = 0.2F)) with scaleOut(animationSpec = spring(dampingRatio = 0.2F))
                        } else {
                            scaleIn(animationSpec = tween(400)) with scaleOut(animationSpec = tween(400))
                        }
                    }
                ) {

                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = stringResource(R.string.order))
                }
            }
        }

        if (showOrderPopup) {
            val coroutineScope = rememberCoroutineScope()
            val youMustConsentToEulaText = stringResource(R.string.you_must_consent_to_the_eula)
            val checkSettingsText = stringResource(R.string.check_user_settings)

            OrderPopup(
                chosenMenusPerDay = chosenMenusPerDay,
                orderingInProgress = orderingInProgress,
                orderingWasSuccessful = orderingWasSuccessful,
                orderingCompleted = orderingCompleted,
                appSettings = appSettings,
                onCancel = { showOrderPopup = false },
                onOrder = {
                    if (!appSettings.consentToEula) {
                        Toast.makeText(context, youMustConsentToEulaText, Toast.LENGTH_SHORT).show()
                        return@OrderPopup
                    }

                    if (!appSettings.isValidForOrderingMenus()) {
                        Toast.makeText(context, checkSettingsText, Toast.LENGTH_SHORT).show()
                        return@OrderPopup
                    }

                    menuRepositoryManager.orderMenus(
                        chosenMenusPerDay = chosenMenusPerDay,
                        appSettings = appSettings,
                        onStartOrdering = {
                            orderingInProgress = true
                            orderingCompleted = false
                            orderingWasSuccessful = false
                        },
                        onNotConnectedToNetwork = {
                            orderingInProgress = false
                            orderingCompleted = true
                            orderingWasSuccessful = false
                            Toast.makeText(context, R.string.not_connected_to_network, Toast.LENGTH_LONG).show()
                        },
                        onErrorInOrderingProcess = {
                            orderingInProgress = false
                            orderingCompleted = true
                            orderingWasSuccessful = false
                            Toast.makeText(context, R.string.error_ordering_menus, Toast.LENGTH_LONG).show()
                        },
                        onSuccess = {
                            orderingInProgress = false
                            orderingCompleted = true
                            orderingWasSuccessful = true

                            onSuccessfulOrder(chosenMenusPerDay)

                            chosenMenusPerDay = chosenMenusPerDay.resetOrderOptionsPerMenu()

                            coroutineScope.launch {
                                delay(250)
                                showOrderPopup = false
                            }
                        }
                    )
                }
            )
        }

        MenusPerDays(
            chosenMenusPerDay = filteredOrders,
            appSettings = appSettings,
            isRefreshing = refreshing,
            onRefresh = {
                refreshing = true
                menuRepositoryManager.getMenusPerDays(
                    force = true,
                    onFetchingData = {
                        refreshing = true
                    },
                    onNotConnectedToNetwork = {
                        refreshing = false
                        Toast.makeText(context, R.string.not_connected_to_network, Toast.LENGTH_LONG).show()
                    },
                    onErrorFetchingMenus = {
                        refreshing = false
                        Toast.makeText(context, R.string.error_fetching_menus, Toast.LENGTH_LONG).show()
                    },
                    onData = { data ->
                        refreshing = false
                        chosenMenusPerDay = data
                    }
                )
            },
            onUpdateOrderCount = { day, menu, orderCount ->
                chosenMenusPerDay = chosenMenusPerDay
                    .toMutableMap()
                    .apply {
                        val oldOrdersPerDay = get(day)
                        val oldTime = oldOrdersPerDay?.get(menu)?.first

                        val newOrdersPerDay = oldOrdersPerDay?.toMutableMap()
                            ?.apply {
                                put(menu, MenuOrderOptions(oldTime, orderCount))
                            }
                        if (newOrdersPerDay != null) {
                            put(day, newOrdersPerDay)
                        }
                    }
            },
            onUpdateTime = { day, menu, time ->
                chosenMenusPerDay = chosenMenusPerDay
                    .toMutableMap()
                    .apply {
                        val oldOrdersPerDay = get(day)
                        val oldOrderCount = oldOrdersPerDay?.get(menu)?.second ?: 0

                        val newOrdersPerDay = oldOrdersPerDay?.toMutableMap()
                            ?.apply {
                                put(menu, MenuOrderOptions(time, oldOrderCount))
                            }
                        if (newOrdersPerDay != null) {
                            put(day, newOrdersPerDay)
                        }
                    }
            }
        )
    }
}
