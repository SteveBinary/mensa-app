package de.dhbw.mensaapp.model


private const val EMAIL_REGEX_STRING = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
val EMAIL_REGEX = Regex(EMAIL_REGEX_STRING)


fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && EMAIL_REGEX.matches(this)
}

fun AppSettings.isValidForOrderingMenus(): Boolean {
    return userEmail.isValidEmail()
            && userFirstName.isNotBlank()
            && userLastName.isNotBlank()
            && consentToEula
}
