package de.dhbw.mensaapp.database.orderhistory

import de.dhbw.mensaapp.model.orderhistory.OrderHistory


class OrderHistoryManager(private val orderHistoryDatabaseManager: OrderHistoryDatabaseManager) {

    fun getAllOrderHistories(): List<OrderHistory> {
        return orderHistoryDatabaseManager.getAllOrderHistories()
    }

    fun replaceAllOrderHistoriesBy(orderHistories: List<OrderHistory>) {
        orderHistoryDatabaseManager.deleteAllOrderHistories()
        orderHistoryDatabaseManager.upsertAllOrderHistories(orderHistories)
    }
}
