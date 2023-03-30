package de.dhbw.mensaapp.model.menu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


typealias ChosenMenusPerDay = Map<Day, MenuOrderOptionsPerMenu>

typealias MenuOrderOptionsPerMenu = Map<Menu, MenuOrderOptions>

typealias MenuOrderOptions = ParcelablePair<TimeSlot?, Int>

@Parcelize
data class ParcelablePair<out A, out B>(val first: @RawValue A, val second: @RawValue B) : Parcelable


fun ChosenMenusPerDay.totalPricePerCategory(): Prices {
    return this
        .flatMap { entry ->
            entry.value.map { menusPerDay ->
                val menu = menusPerDay.key
                val orderNumber = menusPerDay.value.second

                menu.prices * orderNumber.toFloat()
            }
        }
        .sum(defaultUnit = Prices.defaultUnit)
}

fun ChosenMenusPerDay.sorted(): ChosenMenusPerDay {
    val sortedByDays = this.toSortedMap(Day::compareTo)
    val sortedByDaysAndPrices = sortedByDays.toMutableMap()

    sortedByDaysAndPrices.replaceAll { _, menusPerDay ->
        menusPerDay.toSortedMap(compareBy(Menu::prices))
    }

    return sortedByDaysAndPrices
}

fun ChosenMenusPerDay.resetOrderOptionsPerMenu(): ChosenMenusPerDay {
    return this.toMutableMap()
        .mapValues { entry ->
            entry.value.mapValues {
                it.value.copy(first = null, second = 0)
            }
        }
}

fun ChosenMenusPerDay.totalNumberOfOrderedMenus() = this.values.sumOf(MenuOrderOptionsPerMenu::totalNumberOfMenus)

fun MenuOrderOptionsPerMenu.totalNumberOfMenus() = this.values.sumOf(MenuOrderOptions::second)
