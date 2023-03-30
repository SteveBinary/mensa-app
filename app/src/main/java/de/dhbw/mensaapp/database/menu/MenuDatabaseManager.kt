package de.dhbw.mensaapp.database.menu

import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay


interface MenuDatabaseManager {

    fun upsertAllMenusWithDay(menusPerDay: ChosenMenusPerDay)

    fun getAllMenusGroupedByDay(): ChosenMenusPerDay

    fun deleteAllMenus()
}
