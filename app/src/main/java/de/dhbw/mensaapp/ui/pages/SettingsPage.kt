package de.dhbw.mensaapp.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.PriceCategory
import de.dhbw.mensaapp.model.isValidEmail
import de.dhbw.mensaapp.ui.components.input.Dropdown
import de.dhbw.mensaapp.ui.components.input.TextField
import de.dhbw.mensaapp.ui.components.input.Toggle
import de.dhbw.mensaapp.ui.components.input.ToggleWithClickableUrlInLabel


@Composable
fun SettingsPage(appSettings: AppSettings, onAppSettingChange: (AppSettings) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Header {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.refresh_menus),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = stringResource(R.string.settings),
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {

            TextField(text = appSettings.userFirstName, title = stringResource(R.string.first_name), validation = { it.isNotBlank() }) { newFirstName ->
                onAppSettingChange(appSettings.copy(userFirstName = newFirstName))
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(text = appSettings.userLastName, title = stringResource(R.string.last_name), validation = { it.isNotBlank() }) { newLastName ->
                onAppSettingChange(appSettings.copy(userLastName = newLastName))
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(text = appSettings.userEmail, title = stringResource(R.string.email), validation = { it.isValidEmail() }) { newUserEmail ->
                onAppSettingChange(appSettings.copy(userEmail = newUserEmail))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Dropdown(currentItem = appSettings.priceCategory, values = PriceCategory.values().asIterable(), label = { Text(stringResource(R.string.price_category)) }) { newPriceCategory ->
                onAppSettingChange(appSettings.copy(priceCategory = newPriceCategory))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Toggle(checked = appSettings.showPastOrders, text = stringResource(R.string.show_past_orders)) { newShowPastOrders ->
                onAppSettingChange(appSettings.copy(showPastOrders = newShowPastOrders))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Toggle(checked = appSettings.showFoodAdditivesAndAllergens, text = stringResource(R.string.show_food_additives_and_allergens)) { newShowFoodAdditivesAndAllergens ->
                onAppSettingChange(appSettings.copy(showFoodAdditivesAndAllergens = newShowFoodAdditivesAndAllergens))
            }

            Spacer(modifier = Modifier.height(20.dp))

            val eulaTerm = stringResource(R.string.eula_term)
            val acceptEulaText = stringResource(R.string.accept_eula_surrounding_text, eulaTerm)
            val eulaTermStartIndex = acceptEulaText.indexOf(eulaTerm)
            val eulaTermEndIndex = eulaTermStartIndex + eulaTerm.length

            val acceptEulaAnnotatedString = buildAnnotatedString {
                append(acceptEulaText)

                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.surfaceTint,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = eulaTermStartIndex,
                    end = eulaTermEndIndex
                )

                addStringAnnotation(
                    tag = "URL",
                    annotation = appSettings.eulaUrl,
                    start = eulaTermStartIndex,
                    end = eulaTermEndIndex
                )
            }

            ToggleWithClickableUrlInLabel(checked = appSettings.consentToEula, text = acceptEulaAnnotatedString) { newConsentToEula ->
                onAppSettingChange(appSettings.copy(consentToEula = newConsentToEula))
            }
        }
    }
}
