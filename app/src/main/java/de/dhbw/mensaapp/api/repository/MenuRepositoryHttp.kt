package de.dhbw.mensaapp.api.repository

import android.util.Log
import com.google.gson.Gson
import de.dhbw.mensaapp.api.MenuApi
import de.dhbw.mensaapp.api.dto.GetMenusResponseDTO
import de.dhbw.mensaapp.api.dto.OrderMenuResponseDTO
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.menu.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MenuRepositoryHttp(mensaUrl: String, private val mensaId: Int) : MenuRepository {

    private val menuApi: MenuApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(mensaUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        menuApi = retrofit.create(MenuApi::class.java)
    }

    override fun getMenus(
        onFailure: (t: Throwable) -> Unit,
        onResponse: (response: Response<GetMenusResponseDTO>) -> Unit,
    ) {
        menuApi
            .getMenus(mensaId)
            .enqueue(object : Callback<GetMenusResponseDTO> {
                override fun onResponse(call: Call<GetMenusResponseDTO>, response: Response<GetMenusResponseDTO>) = onResponse(response)
                override fun onFailure(call: Call<GetMenusResponseDTO>, t: Throwable) = onFailure(t)
            })
    }

    override fun orderMenus(
        chosenMenusPerDay: ChosenMenusPerDay,
        appSettings: AppSettings,
        onFailure: (t: Throwable) -> Unit,
        onResponse: (response: Response<OrderMenuResponseDTO>) -> Unit
    ) {

        chosenMenusPerDay
            .filter { it.value.totalNumberOfMenus() > 0 }
            .forEach { (day, ordersPerDay) ->
                val ordersGroupedByTime = ordersPerDay.entries
                    .filter { entry -> entry.value.first != null }
                    .filter { entry -> entry.value.second > 0 }
                    .groupBy { entry -> entry.value.first!! }
                    .mapValues { groupedOrderOptions ->
                        groupedOrderOptions.value.map { entry ->
                            entry.key to entry.value.second
                        }
                    }

                ordersGroupedByTime.forEach { (time, menusAndOrderNumber) ->
                    val payloadString = buildPayloadStringForOneDayWithItsMenus(appSettings, day, time, menusAndOrderNumber)
                    val requestBody = RequestBody.create(MediaType.get("application/x-www-form-urlencoded"), payloadString)

                    Log.d("SEND_ORDER", payloadString)

                    menuApi
                        .orderMenus(requestBody)
                        .enqueue(object : Callback<OrderMenuResponseDTO> {
                            override fun onResponse(call: Call<OrderMenuResponseDTO>, response: Response<OrderMenuResponseDTO>) = onResponse(response)
                            override fun onFailure(call: Call<OrderMenuResponseDTO>, t: Throwable) = onFailure(t)
                        })
                }
            }
    }

    private fun buildPayloadStringForOneDayWithItsMenus(appSettings: AppSettings, day: Day, timeSlot: TimeSlot, menusAndOrderNumber: List<Pair<Menu, Int>>): String {
        return buildString {
            append(
                """
                client[einrichtung_val]=${appSettings.mensaId}
                &client[vorname]=${appSettings.userFirstName.trim()}
                &client[name]=${appSettings.userLastName.trim()}
                &client[email]=${appSettings.userEmail.trim()}
                &client[deliver_time_val]=${timeSlot.formattedString}
                &client[date_iso]=${day.date}
            """.trimIndent().replace("\n", "")
            )

            menusAndOrderNumber.forEach { (menu, orderNumber) ->
                if (orderNumber > 0) {
                    append("&basket_positions[${menu.id}]=${orderNumber}")
                }
            }
        }
    }
}
