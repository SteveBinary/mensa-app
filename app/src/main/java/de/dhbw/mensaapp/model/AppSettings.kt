package de.dhbw.mensaapp.model

import android.content.SharedPreferences
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.core.content.edit
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.SHARED_PREFERENCES_KEYS
import de.dhbw.mensaapp.ui.TranslatableComposable
import kotlinx.parcelize.Parcelize


@Parcelize
enum class PriceCategory : Parcelable, TranslatableComposable {
    Student, Employee, Guest;

    @Composable
    override fun translatedString() = when (this) {
        Student -> stringResource(R.string.price_category_student)
        Employee -> stringResource(R.string.price_category_employee)
        Guest -> stringResource(R.string.price_category_guest)
    }
}


@Parcelize
data class AppSettings(
    val mensaUrl: String,
    val mensaId: Int,
    val userEmail: String,
    val userFirstName: String,
    val userLastName: String,
    val priceCategory: PriceCategory,
    val showPastOrders: Boolean,
    val consentToEula: Boolean,
    val eulaUrl: String,
    val showFoodAdditivesAndAllergens: Boolean,
) : Parcelable {

    companion object
}


fun AppSettings.saveToSharedPreferences(sharedPreferences: SharedPreferences) {
    sharedPreferences.edit {
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.MENSA_URL, mensaUrl)
        putInt(SHARED_PREFERENCES_KEYS.APP_SETTINGS.MENSA_ID, mensaId)
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_EMAIL, userEmail.trim())
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_FIRST_NAME, userFirstName.trim())
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_LAST_NAME, userLastName.trim())
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.PRICE_CATEGORY, priceCategory.toString())
        putBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.CONSENT_TO_EULA, consentToEula)
        putBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.SHOW_PAST_ORDERS, showPastOrders)
        putString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.EULA_URL, eulaUrl)
        putBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.SHOW_FOOD_ADDITIVES_AND_ALLERGENS, showFoodAdditivesAndAllergens)
    }
}

fun AppSettings.Companion.getFromSharedPreferences(sharedPreferences: SharedPreferences, defaults: AppSettings): AppSettings {
    val mensaUrl = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.MENSA_URL, defaults.mensaUrl) ?: defaults.mensaUrl
    val mensaId = sharedPreferences.getInt(SHARED_PREFERENCES_KEYS.APP_SETTINGS.MENSA_ID, defaults.mensaId)
    val userEmail = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_EMAIL, defaults.userEmail) ?: defaults.userEmail
    val userFirstName = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_FIRST_NAME, defaults.userFirstName) ?: defaults.userFirstName
    val userLastName = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.USER_LAST_NAME, defaults.userLastName) ?: defaults.userLastName
    val priceSettings = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.PRICE_CATEGORY, defaults.priceCategory.toString()) ?: defaults.priceCategory.toString()
    val consentToEula = sharedPreferences.getBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.CONSENT_TO_EULA, defaults.consentToEula)
    val showPastOrders = sharedPreferences.getBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.SHOW_PAST_ORDERS, defaults.showPastOrders)
    val eulaUrl = sharedPreferences.getString(SHARED_PREFERENCES_KEYS.APP_SETTINGS.EULA_URL, defaults.eulaUrl) ?: defaults.eulaUrl
    val showFoodAdditivesAndAllergens = sharedPreferences.getBoolean(SHARED_PREFERENCES_KEYS.APP_SETTINGS.SHOW_FOOD_ADDITIVES_AND_ALLERGENS, defaults.showFoodAdditivesAndAllergens)

    return AppSettings(
        mensaUrl = mensaUrl,
        mensaId = mensaId,
        userEmail = userEmail,
        userFirstName = userFirstName,
        userLastName = userLastName,
        priceCategory = PriceCategory.valueOf(priceSettings),
        consentToEula = consentToEula,
        showPastOrders = showPastOrders,
        eulaUrl = eulaUrl,
        showFoodAdditivesAndAllergens = showFoodAdditivesAndAllergens
    )
}
