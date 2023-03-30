package de.dhbw.mensaapp.api

import android.net.ConnectivityManager
import android.util.Log
import de.dhbw.mensaapp.api.dto.toOrders
import de.dhbw.mensaapp.api.repository.MenuRepository
import de.dhbw.mensaapp.database.menu.MenuDatabaseManager
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay
import de.dhbw.mensaapp.model.menu.sorted
import de.dhbw.mensaapp.network.isConnected
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.time.Duration


class MenuRepositoryManager(
    private val menuRepository: MenuRepository,
    private val menuDatabaseManager: MenuDatabaseManager,
    private val connectivityManager: ConnectivityManager,
    private val refreshThreshold: Duration,
    private var lastCacheRefresh: LocalDateTime,
    private val saveLastCacheRefresh: (LocalDateTime) -> Unit
) {

    private var cachedMenus: ChosenMenusPerDay
    private var currentlyFetchingData = false
    private var currentlyOrdering = false

    init {
        Log.d("CACHE", "Get data from database because of initial load.")
        cachedMenus = menuDatabaseManager.getAllMenusGroupedByDay().sorted()
    }

    private fun isCacheExpired(): Boolean {
        val nowEpoch = LocalDateTime.now().toEpochSecond(ZoneOffset.MIN)
        val lastCacheRefreshEpoch = lastCacheRefresh.toEpochSecond(ZoneOffset.MIN)

        return nowEpoch - lastCacheRefreshEpoch > refreshThreshold.inWholeSeconds
    }

    private fun refresh(
        onFetchingData: () -> Unit,
        onErrorFetchingMenus: () -> Unit,
        onData: (ChosenMenusPerDay) -> Unit
    ) {
        currentlyFetchingData = true
        onFetchingData()

        menuRepository.getMenus(
            onFailure = { throwable ->
                Log.w("CACHE", "An error occurred while fetching new data for the cache!", throwable)
                currentlyFetchingData = false
                onErrorFetchingMenus()
                onData(cachedMenus)
            },
            onResponse = { response ->
                currentlyFetchingData = false
                val maybeMenusPerDays = response.body()?.toOrders()
                if (maybeMenusPerDays == null) {
                    Log.d("CACHE", "An error occurred while fetching new data for the cache!")
                    onErrorFetchingMenus()
                    onData(cachedMenus)
                } else {
                    Log.d("CACHE", "Got new data for cache!")
                    cachedMenus = maybeMenusPerDays.sorted()

                    menuDatabaseManager.deleteAllMenus()
                    menuDatabaseManager.upsertAllMenusWithDay(cachedMenus)

                    val now = LocalDateTime.now()
                    lastCacheRefresh = now
                    saveLastCacheRefresh(now)

                    Log.d("CACHE", "Cache refreshing successful. Latest cache refresh is now: $now")
                    onData(cachedMenus)
                }
            }
        )
    }

    fun getMenusPerDays(
        onFetchingData: () -> Unit,
        onNotConnectedToNetwork: () -> Unit,
        onErrorFetchingMenus: () -> Unit,
        onData: (ChosenMenusPerDay) -> Unit,
        force: Boolean = false
    ) {

        if (currentlyFetchingData) {
            Log.d("CACHE", "Currently fetching data. Wont try to refresh cache again.")
            onData(cachedMenus)
            return
        }

        if (!connectivityManager.isConnected()) {
            Log.w("CACHE", "Wont refresh cache because network is not connected!")
            onNotConnectedToNetwork()
            onData(cachedMenus)
            return
        }

        Log.d("CACHE", "Getting menus...")

        if (force || isCacheExpired()) {
            Log.d("CACHE", "Will try to refresh cache.")
            refresh(onFetchingData, onErrorFetchingMenus, onData)
            return
        }

        Log.d("CACHE", "Wont refresh cache because it is neither expired nor forced.")
        onData(cachedMenus)
    }

    fun orderMenus(
        chosenMenusPerDay: ChosenMenusPerDay,
        appSettings: AppSettings,
        onStartOrdering: () -> Unit,
        onNotConnectedToNetwork: () -> Unit,
        onErrorInOrderingProcess: () -> Unit,
        onSuccess: () -> Unit
    ) {

        if (currentlyOrdering) {
            Log.d("ORDER", "Currently ordering menus. Wont try to order again.")
            return
        }

        if (!connectivityManager.isConnected()) {
            Log.w("ORDER", "Wont order menus because network is not connected!")
            onNotConnectedToNetwork()
            return
        }

        Log.d("ORDER", "Ordering menus...")
        currentlyOrdering = true
        onStartOrdering()

        menuRepository.orderMenus(
            chosenMenusPerDay = chosenMenusPerDay,
            appSettings = appSettings,
            onFailure = { throwable ->
                Log.w("ORDER", "An error occurred while ordering menus!", throwable)
                currentlyOrdering = false
                onErrorInOrderingProcess()
            },
            onResponse = { response ->
                currentlyOrdering = false
                val maybeOrderMenusResponse = response.body()
                if (maybeOrderMenusResponse == null || !maybeOrderMenusResponse.isSuccess()) {
                    Log.d("ORDER", "An error occurred while ordering menus!")
                    onErrorInOrderingProcess()
                } else {
                    Log.d("ORDER", "Ordering was successful.")
                    onSuccess()
                }
            }
        )
    }
}
