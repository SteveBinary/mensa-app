package de.dhbw.mensaapp.database.orderhistory

import de.dhbw.mensaapp.model.orderhistory.OrderHistory


interface OrderHistoryDatabaseManager {

    fun upsertAllOrderHistories(orderHistories: List<OrderHistory>)

    fun getAllOrderHistories(): List<OrderHistory>

    fun deleteAllOrderHistories()
}
