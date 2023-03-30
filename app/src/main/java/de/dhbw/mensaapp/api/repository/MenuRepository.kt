package de.dhbw.mensaapp.api.repository

import de.dhbw.mensaapp.api.dto.GetMenusResponseDTO
import de.dhbw.mensaapp.api.dto.OrderMenuResponseDTO
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay
import retrofit2.Response


interface MenuRepository {

    fun getMenus(
        onFailure: (t: Throwable) -> Unit,
        onResponse: (response: Response<GetMenusResponseDTO>) -> Unit,
    )

    fun orderMenus(
        chosenMenusPerDay: ChosenMenusPerDay,
        appSettings: AppSettings,
        onFailure: (t: Throwable) -> Unit,
        onResponse: (response: Response<OrderMenuResponseDTO>) -> Unit,
    )
}
