package de.dhbw.mensaapp.api.dto

import com.google.gson.annotations.SerializedName
import de.dhbw.mensaapp.model.menu.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


data class GetMenusResponseDTO(
    @SerializedName("mensaname")
    val mensaName: String,
    @SerializedName("result")
    val result: List<MenusPerDayDTO>
)

data class MenusPerDayDTO(
    @SerializedName("essen")
    val menus: List<MenuDTO>?,
    @SerializedName("tag")
    val day: DayDTO
)

data class MenuDTO(
    @SerializedName("a_id")
    val articleId: String,
    @SerializedName("description_clean")
    val descriptionClean: String?,
    @SerializedName("kennzeichnungen")
    val foodAdditivesAndAllergens: String,
    @SerializedName("preis1")
    val price1: String,
    @SerializedName("preis2")
    val price2: String,
    @SerializedName("preis3")
    val price3: String,
    @SerializedName("title_clean")
    val titleClean: String?
)

data class DayDTO(
    @SerializedName("datum_iso")
    val dateIso: String,
)

fun DayDTO.toDay(): Day {
    val localDate = try {
        LocalDate.parse(dateIso, DateTimeFormatter.ISO_LOCAL_DATE)
    } catch (e: DateTimeParseException) {
        LocalDate.MIN
    }

    return Day(localDate)
}

fun MenuDTO.toMenu(): Menu {
    fun cleanText(text: String?) = text
        ?.replace("-", "\u2010")  // for line breaks after hyphens
        ?.replace(", und", " und")
        ?.replace(" ,", ",")
        ?.replace(",,", ",")
        ?.trim()

    return Menu(
        id = articleId,
        name = cleanText(titleClean) ?: "ERROR",
        subTitle = cleanText(descriptionClean) ?: "",
        prices = Prices(
            unit = Prices.defaultUnit,
            student = price1.replace(",", ".").toFloatOrNull() ?: 0.0F,
            employee = price2.replace(",", ".").toFloatOrNull() ?: 0.0F,
            guest = price3.replace(",", ".").toFloatOrNull() ?: 0.0F,
        ),
        foodAdditivesAndAllergens = foodAdditivesAndAllergens.replace(",", ", ").trim(),
    )
}

fun GetMenusResponseDTO.toOrders(): ChosenMenusPerDay {
    return result
        .associateBy(MenusPerDayDTO::day)
        .mapKeys { entry ->
            entry.key.toDay()
        }
        .mapValues { entry ->
            val menuDTOs = entry.value.menus ?: emptyList()
            menuDTOs.map(MenuDTO::toMenu)
                .associateWith { ParcelablePair(null, 0) }
        }.let {
            if (it.values.sumOf { a -> a.values.size } == 0) {
                emptyMap()
            } else {
                it
            }
        }
}
