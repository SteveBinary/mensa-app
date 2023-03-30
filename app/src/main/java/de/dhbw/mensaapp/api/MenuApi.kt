package de.dhbw.mensaapp.api

import de.dhbw.mensaapp.api.dto.GetMenusResponseDTO
import de.dhbw.mensaapp.api.dto.OrderMenuResponseDTO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface MenuApi {

    @GET("/getdata.php?json=1")
    fun getMenus(@Query("mensa_id") mensaId: Int): Call<GetMenusResponseDTO>

    @POST("/setDataMensaTogo.php")
    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    fun orderMenus(@Body payload: RequestBody): Call<OrderMenuResponseDTO>
}
