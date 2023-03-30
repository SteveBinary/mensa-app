package de.dhbw.mensaapp.model.orderhistory

import android.os.Parcelable
import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay
import de.dhbw.mensaapp.model.menu.Day
import de.dhbw.mensaapp.model.menu.TimeSlot
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.time.Duration


typealias OrderHistoriesPerDay = Map<Day, List<OrderHistory>>

@Parcelize
data class OrderHistory(
    val menuName: String,
    val menuSubtitle: String,
    val day: Day,
    val timeSlot: TimeSlot,
    val orderCount: Int
) : Parcelable

fun ChosenMenusPerDay.toOrderHistoryList(): List<OrderHistory> {
    return this
        .flatMap { (day, menuOrderOptionsPerMenu) ->
            menuOrderOptionsPerMenu
                .filter { it.value.first != null }
                .filter { it.value.second > 0 }
                .map { (menu, orderOptions) ->
                    OrderHistory(
                        menuName = menu.name,
                        menuSubtitle = menu.subTitle,
                        day = day,
                        timeSlot = orderOptions.first!!,
                        orderCount = orderOptions.second
                    )
                }
        }
}

fun List<OrderHistory>.numberOfOrdersForTodayFromBefore(offset: Duration): Int {
    val nowBeforeOffset = LocalTime.now().minusSeconds(offset.inWholeSeconds)
    val today = LocalDate.now()

    return this
        .filter { orderHistory -> orderHistory.day.date == today }
        .filter { orderHistory -> orderHistory.timeSlot.time > nowBeforeOffset }
        .sumOf { orderHistory -> orderHistory.orderCount }
}

fun List<OrderHistory>.excludePastOrdersBefore(dateTime: LocalDateTime): List<OrderHistory> {
    return this.filter { orderHistory ->
        val timeSlotBeginPlusOffset = LocalDateTime.of(orderHistory.day.date, orderHistory.timeSlot.time)
        dateTime < timeSlotBeginPlusOffset
    }
}

fun List<OrderHistory>.sortedOrderHistoriesPerDayBeforeOffset(includePastOrders: Boolean, offset: Duration): OrderHistoriesPerDay {
    val startingTime = LocalDateTime.now().minusSeconds(offset.inWholeSeconds)

    return this
        .runIf(!includePastOrders) { excludePastOrdersBefore(startingTime) }
        .groupBy { it.day }
        .toSortedMap(Day::compareTo)
        .mapValues { (_, orderHistories) ->
            orderHistories.sortedWith(compareBy({ it.day.date }, { it.timeSlot.time }, { it.orderCount }, { it.menuName }))
        }
}

fun <T> T.runIf(condition: Boolean, action: T.() -> T): T {
    if (condition) {
        return this.run(action)
    }

    return this
}
