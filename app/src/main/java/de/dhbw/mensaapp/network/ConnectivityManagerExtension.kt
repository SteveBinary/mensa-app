package de.dhbw.mensaapp.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities


fun ConnectivityManager.isConnected(): Boolean {
    val network: Network = this.activeNetwork ?: return false
    val capabilities = this.getNetworkCapabilities(network) ?: return false

    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}
