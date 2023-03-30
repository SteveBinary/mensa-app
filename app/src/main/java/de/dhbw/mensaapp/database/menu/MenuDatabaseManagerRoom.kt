package de.dhbw.mensaapp.database.menu

import de.dhbw.mensaapp.model.menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class MenuDatabaseManagerRoom(private val menuEntityDatabase: MenuEntityDatabase) : MenuDatabaseManager {

    override fun upsertAllMenusWithDay(menusPerDay: ChosenMenusPerDay) {
        CoroutineScope(Dispatchers.IO).launch {
            menuEntityDatabase.menuDao.upsertAllMenuEntity(
                menusPerDay.flatMap { (day, menus) ->
                    menus.map { entry ->
                        MenuEntity(
                            menuId = entry.key.id,
                            name = entry.key.name,
                            subTitle = entry.key.subTitle,
                            priceUnit = entry.key.prices.unit,
                            priceStudent = entry.key.prices.student,
                            priceEmployee = entry.key.prices.employee,
                            priceGuest = entry.key.prices.guest,
                            foodAdditivesAndAllergens = entry.key.foodAdditivesAndAllergens,
                            epochDay = day.date.toEpochDay()
                        )
                    }
                }
            )
        }
    }

    override fun getAllMenusGroupedByDay(): ChosenMenusPerDay {
        return menuEntityDatabase.menuDao
            .getMenuEntities()
            .map { menuEntity -> menuEntity.getDayAndMenu() }
            .groupBy { entry -> entry.first }
            .mapValues { entry ->
                entry.value
                    .map { it.second }
                    .associateWith { ParcelablePair(null, 0) }
            }
    }

    override fun deleteAllMenus() {
        menuEntityDatabase.clearAllTables()
    }
}


fun MenuEntity.getDayAndMenu(): Pair<Day, Menu> {
    val day = Day(LocalDate.ofEpochDay(this.epochDay))
    val menu = Menu(
        id = this.menuId,
        name = this.name,
        subTitle = this.subTitle,
        prices = Prices(
            unit = this.priceUnit,
            student = this.priceStudent,
            employee = this.priceEmployee,
            guest = this.priceGuest
        ),
        foodAdditivesAndAllergens = this.foodAdditivesAndAllergens
    )

    return day to menu
}
