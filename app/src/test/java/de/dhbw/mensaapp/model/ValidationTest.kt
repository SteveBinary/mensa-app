package de.dhbw.mensaapp.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ValidationTest {

    @Test
    fun testEmailRegex_returnsTrueForValidEmail() {
        // given
        val validEmail = "test@example.com"
        // when
        val result = validEmail.matches(EMAIL_REGEX)
        // then
        assertTrue(result)
    }

    @Test
    fun testEmailRegex_returnsFalseForInvalidEmail() {
        // given
        val invalidEmail = "not a valid email"
        // when
        val result = invalidEmail.matches(EMAIL_REGEX)
        // then
        assertFalse(result)
    }

    @Test
    fun testEmailRegex_returnsFalseForMissingUsername() {
        // given
        val invalidEmail = "@example.com"
        // when
        val result = invalidEmail.matches(EMAIL_REGEX)
        // then
        assertFalse(result)
    }

    @Test
    fun testEmailRegex_returnsFalseForMissingDomain() {
        // given
        val invalidEmail = "test@"
        // when
        val result = invalidEmail.matches(EMAIL_REGEX)
        // then
        assertFalse(result)
    }

    @Test
    fun testEmailRegex_returnsFalseForInvalidCharacters() {
        // given
        val invalidEmail = "test@example.çom"
        // when
        val result = invalidEmail.matches(EMAIL_REGEX)
        // then
        assertFalse(result)
    }

    @Test
    fun testEmailRegex_returnsFalseForTooLongDomain() {
        // given
        val invalidEmail = "test@" + "a".repeat(256) + ".com"
        // when
        val result = invalidEmail.matches(EMAIL_REGEX)
        // then
        assertFalse(result)
    }

    @Test
    fun testIsValidForOrderingMenus_returnsTrueWhenAllFieldsAreNotBlank() {
        val appSettings = AppSettings(
            mensaUrl = "",
            mensaId = 15,
            userEmail = "test@example.com",
            userFirstName = "John",
            userLastName = "Doe",
            priceCategory = PriceCategory.Student,
            consentToEula = true,
            showPastOrders = false,
            eulaUrl = "",
            showFoodAdditivesAndAllergens = true
        )

        assertTrue(appSettings.isValidForOrderingMenus())
    }

    @Test
    fun testIsValidForOrderingMenus_returnsFalseWhenEmailIsBlank() {
        val appSettings = AppSettings(
            mensaUrl = "",
            mensaId = 15,
            userEmail = "",
            userFirstName = "John",
            userLastName = "Doe",
            priceCategory = PriceCategory.Student,
            consentToEula = true,
            showPastOrders = false,
            eulaUrl = "",
            showFoodAdditivesAndAllergens = true
        )

        assertFalse(appSettings.isValidForOrderingMenus())
    }

    @Test
    fun testIsValidForOrderingMenus_returnsFalseWhenFirstNameIsBlank() {
        val appSettings = AppSettings(
            mensaUrl = "",
            mensaId = 15,
            userEmail = "test@example.com",
            userFirstName = "",
            userLastName = "Doe",
            priceCategory = PriceCategory.Student,
            consentToEula = true,
            showPastOrders = false,
            eulaUrl = "",
            showFoodAdditivesAndAllergens = true
        )

        assertFalse(appSettings.isValidForOrderingMenus())

    }

    @Test
    fun testIsValidForOrderingMenus_returnsFalseWhenLastNameIsBlank() {
        val appSettings = AppSettings(
            mensaUrl = "",
            mensaId = 15,
            userEmail = "test@example.com",
            userFirstName = "John",
            userLastName = "",
            priceCategory = PriceCategory.Student,
            consentToEula = true,
            showPastOrders = false,
            eulaUrl = "",
            showFoodAdditivesAndAllergens = true
        )

        assertFalse(appSettings.isValidForOrderingMenus())
    }

    @Test
    fun testIsValidForOrderingMenus_returnsFalseWhenConsentToEulaIsFalse() {
        val appSettings = AppSettings(
            mensaUrl = "",
            mensaId = 15,
            userEmail = "test@example.com",
            userFirstName = "John",
            userLastName = "Doe",
            priceCategory = PriceCategory.Student,
            consentToEula = false,
            showPastOrders = false,
            eulaUrl = "",
            showFoodAdditivesAndAllergens = true
        )

        assertFalse(appSettings.isValidForOrderingMenus())
    }
}