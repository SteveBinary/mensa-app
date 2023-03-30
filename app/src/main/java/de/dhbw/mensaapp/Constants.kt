package de.dhbw.mensaapp

import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.PriceCategory
import kotlin.time.Duration.Companion.hours


val CACHE_REFRESH_TIMEOUT = 1.hours
const val MENU_DATABASE_FILE = "menus.db"
const val ORDER_HISTORY_DATABASE_FILE = "orders.db"

val DEFAULT_APP_SETTINGS = AppSettings(
    mensaUrl = "https://stwulm.my-mensa.de",
    mensaId = 15,
    userEmail = "",
    userFirstName = "",
    userLastName = "",
    priceCategory = PriceCategory.Student,
    showPastOrders = false,
    consentToEula = false,
    eulaUrl = "https://studierendenwerk-ulm.de/c-essen/bedingungen/#nutzungsvereinbarungen",
    showFoodAdditivesAndAllergens = false,
)

@Suppress("ClassName")
object SHARED_PREFERENCES_KEYS {

    const val PREFERENCE_FILE = "SHARED_PREFERENCES"

    object APP_SETTINGS {
        const val MENSA_URL = "APP_SETTINGS_MENSA_URL"
        const val MENSA_ID = "APP_SETTINGS_MENSA_ID"
        const val USER_EMAIL = "APP_SETTINGS_USER_EMAIL"
        const val USER_FIRST_NAME = "APP_SETTINGS_USER_FIRST_NAME"
        const val USER_LAST_NAME = "APP_SETTINGS_USER_LAST_NAME"
        const val PRICE_CATEGORY = "APP_SETTINGS_PRICE_CATEGORY"
        const val CONSENT_TO_EULA = "APP_SETTINGS_CONSENT_TO_EULA"
        const val SHOW_PAST_ORDERS = "APP_SETTINGS_SHOW_PAST_ORDERS"
        const val EULA_URL = "APP_SETTINGS_EULA_URL"
        const val SHOW_FOOD_ADDITIVES_AND_ALLERGENS = "APP_SETTINGS_SHOW_FOOD_ADDITIVES_AND_ALLERGENS"
    }

    object LAST_CACHE_TIME {
        const val EPOCH_SECONDS = "LAST_CACHE_TIME_EPOCH_SECONDS"
    }
}
