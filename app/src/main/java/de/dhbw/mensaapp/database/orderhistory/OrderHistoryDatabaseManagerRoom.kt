package de.dhbw.mensaapp.database.orderhistory

import de.dhbw.mensaapp.model.menu.Day
import de.dhbw.mensaapp.model.menu.TimeSlot
import de.dhbw.mensaapp.model.orderhistory.OrderHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class OrderHistoryDatabaseManagerRoom(private val orderHistoryEntityDatabase: OrderHistoryEntityDatabase) : OrderHistoryDatabaseManager {

    override fun upsertAllOrderHistories(orderHistories: List<OrderHistory>) {
        CoroutineScope(Dispatchers.IO).launch {
            orderHistoryEntityDatabase.orderHistoryDao.upsertAllOrderEntities(
                orderHistories.map { orderHistory ->
                    OrderHistoryEntity(
                        name = orderHistory.menuName,
                        subtitle = orderHistory.menuSubtitle,
                        epochDay = orderHistory.day.date.toEpochDay(),
                        timeSlot = orderHistory.timeSlot.name,
                        count = orderHistory.orderCount
                    )
                }
            )
        }
    }

    override fun getAllOrderHistories(): List<OrderHistory> {
        return orderHistoryEntityDatabase.orderHistoryDao
            .getOrderEntities()
            .map { orderHistoryEntity ->
                OrderHistory(
                    menuName = orderHistoryEntity.name,
                    menuSubtitle = orderHistoryEntity.subtitle,
                    day = Day(LocalDate.ofEpochDay(orderHistoryEntity.epochDay)),
                    timeSlot = TimeSlot.valueOf(orderHistoryEntity.timeSlot),
                    orderCount = orderHistoryEntity.count
                )
            }
    }

    override fun deleteAllOrderHistories() {
        orderHistoryEntityDatabase.clearAllTables()
    }
}
