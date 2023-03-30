package de.dhbw.mensaapp.api.dto

import com.google.gson.annotations.SerializedName


data class OrderMenuResponseDTO(
    @SerializedName("type")
    val type: String,
    @SerializedName("text")
    val text: String,
) {
    fun isSuccess() = type.trim().lowercase() == "success"
}
