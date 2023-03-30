package de.dhbw.mensaapp

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.edit
import androidx.room.Room
import de.dhbw.mensaapp.api.MenuRepositoryManager
import de.dhbw.mensaapp.api.repository.MenuRepositoryHttp
import de.dhbw.mensaapp.database.menu.MenuDatabaseManager
import de.dhbw.mensaapp.database.menu.MenuDatabaseManagerRoom
import de.dhbw.mensaapp.database.menu.MenuEntityDatabase
import de.dhbw.mensaapp.database.orderhistory.OrderHistoryDatabaseManager
import de.dhbw.mensaapp.database.orderhistory.OrderHistoryDatabaseManagerRoom
import de.dhbw.mensaapp.database.orderhistory.OrderHistoryEntityDatabase
import de.dhbw.mensaapp.database.orderhistory.OrderHistoryManager
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.getFromSharedPreferences
import de.dhbw.mensaapp.model.orderhistory.OrderHistory
import de.dhbw.mensaapp.model.orderhistory.numberOfOrdersForTodayFromBefore
import de.dhbw.mensaapp.model.orderhistory.toOrderHistoryList
import de.dhbw.mensaapp.model.saveToSharedPreferences
import de.dhbw.mensaapp.ui.components.navigation.MainNavigation
import de.dhbw.mensaapp.ui.components.navigation.NavigationItem
import de.dhbw.mensaapp.ui.pages.MenuPage
import de.dhbw.mensaapp.ui.pages.OrdersPage
import de.dhbw.mensaapp.ui.pages.SettingsPage
import de.dhbw.mensaapp.ui.theme.MensaAppTheme
import de.dhbw.mensaapp.widget.sendNewOrderCountToWidget
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.time.Duration.Companion.hours


class MainActivity : ComponentActivity() {

    private val menuDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuEntityDatabase::class.java,
            MENU_DATABASE_FILE
        ).allowMainThreadQueries().build()
    }

    private val orderHistoryDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            OrderHistoryEntityDatabase::class.java,
            ORDER_HISTORY_DATABASE_FILE
        ).allowMainThreadQueries().build()
    }

    private lateinit var orderHistoryDatabaseManager: OrderHistoryDatabaseManager
    private lateinit var orderHistoryManager: OrderHistoryManager
    private lateinit var menuDatabaseManager: MenuDatabaseManager
    private lateinit var menuRepositoryManager: MenuRepositoryManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val connectivityManager: ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val lastCacheRefresh = getLastCacheRefreshFromSharedPreferences()
        val initialAppSettings = AppSettings.getFromSharedPreferences(
            sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_KEYS.PREFERENCE_FILE, MODE_PRIVATE),
            defaults = DEFAULT_APP_SETTINGS
        )

        Log.d("CACHE", "MainActivity created. Last cache refresh: $lastCacheRefresh")

        orderHistoryDatabaseManager = OrderHistoryDatabaseManagerRoom(orderHistoryDatabase)
        orderHistoryManager = OrderHistoryManager(orderHistoryDatabaseManager)

        val initialOrderHistories = orderHistoryManager.getAllOrderHistories()
        refreshWidget(initialOrderHistories)

        menuDatabaseManager = MenuDatabaseManagerRoom(menuDatabase)
        menuRepositoryManager = MenuRepositoryManager(
            menuRepository = MenuRepositoryHttp(initialAppSettings.mensaUrl, initialAppSettings.mensaId),
            menuDatabaseManager = menuDatabaseManager,
            connectivityManager = connectivityManager,
            refreshThreshold = CACHE_REFRESH_TIMEOUT,
            lastCacheRefresh = lastCacheRefresh,
            saveLastCacheRefresh = ::saveLastCacheRefreshToSharedPreferences
        )

        setContent {
            MensaAppTheme {

                var appSettings by rememberSaveable { mutableStateOf(initialAppSettings) }
                var orderHistories by rememberSaveable { mutableStateOf(initialOrderHistories) }

                val tabs = listOf(
                    NavigationItem(Icons.Filled.Restaurant, R.string.menus, "Menus") {
                        MenuPage(appSettings, menuRepositoryManager, applicationContext) { orderedMenusPerDay ->

                            val oldOrderCount = orderHistories.sumOf { it.orderCount }

                            orderHistories = orderHistories.toMutableList().apply {
                                addAll(orderedMenusPerDay.toOrderHistoryList())
                            }

                            val newOrderCount = orderHistories.sumOf { it.orderCount }

                            refreshWidget(orderHistories)

                            if (newOrderCount != oldOrderCount) {
                                orderHistoryManager.replaceAllOrderHistoriesBy(orderHistories)
                            }
                        }
                    }, NavigationItem(Icons.Filled.List, R.string.orders, "Orders") {

                        OrdersPage(orderHistories, appSettings)
                    }, NavigationItem(Icons.Filled.Settings, R.string.settings, "Settings") {

                        SettingsPage(appSettings) { newAppSettings ->
                            appSettings = newAppSettings
                            newAppSettings.saveToSharedPreferences(getSharedPreferences(SHARED_PREFERENCES_KEYS.PREFERENCE_FILE, MODE_PRIVATE))
                        }
                    }
                )

                Surface(modifier = Modifier.fillMaxSize()) {
                    MainNavigation(tabs)
                }
            }
        }
    }

    private fun getLastCacheRefreshFromSharedPreferences(): LocalDateTime {
        val preferences = getSharedPreferences(SHARED_PREFERENCES_KEYS.PREFERENCE_FILE, MODE_PRIVATE)
        val lastCacheRefreshEpochSeconds = preferences.getLong(SHARED_PREFERENCES_KEYS.LAST_CACHE_TIME.EPOCH_SECONDS, 0L)

        return LocalDateTime.ofEpochSecond(lastCacheRefreshEpochSeconds, 0, ZoneOffset.MIN)
    }

    private fun saveLastCacheRefreshToSharedPreferences(time: LocalDateTime) {
        val preferences = getSharedPreferences(SHARED_PREFERENCES_KEYS.PREFERENCE_FILE, MODE_PRIVATE)
        preferences.edit {
            putLong(SHARED_PREFERENCES_KEYS.LAST_CACHE_TIME.EPOCH_SECONDS, time.toEpochSecond(ZoneOffset.MIN))
        }
    }

    private fun refreshWidget(orderHistories: List<OrderHistory>) {
        val remainingOrdersForToday = orderHistories.numberOfOrdersForTodayFromBefore(1.hours)
        sendNewOrderCountToWidget(applicationContext, remainingOrdersForToday)
    }
}
