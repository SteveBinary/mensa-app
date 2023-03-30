package de.dhbw.mensaapp.ui.components.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.PriceCategory
import de.dhbw.mensaapp.model.menu.ChosenMenusPerDay
import de.dhbw.mensaapp.model.menu.totalNumberOfOrderedMenus
import de.dhbw.mensaapp.model.menu.totalPricePerCategory


val popupHeight = 250.dp

@Composable
fun OrderPopup(
    chosenMenusPerDay: ChosenMenusPerDay,
    orderingInProgress: Boolean,
    orderingWasSuccessful: Boolean,
    orderingCompleted: Boolean,
    appSettings: AppSettings,
    onCancel: () -> Unit,
    onOrder: () -> Unit
) {
    val totalPrice by remember {
        derivedStateOf {
            chosenMenusPerDay.totalPricePerCategory()
        }
    }
    val numberOfChosenMenus by remember {
        derivedStateOf {
            chosenMenusPerDay.totalNumberOfOrderedMenus()
        }
    }

    Box {

        Popup(
            alignment = Alignment.TopCenter,
            onDismissRequest = onCancel,
            properties = PopupProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                focusable = true,
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.905F)
                    .height(popupHeight)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = MaterialTheme.colorScheme.surfaceTint,
                        spotColor = MaterialTheme.colorScheme.surfaceTint
                    )
                    .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(16.dp))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(stringResource(id = R.string.price_category) + ":", color = MaterialTheme.colorScheme.onBackground)

                            when (appSettings.priceCategory) {
                                PriceCategory.Student -> Text(stringResource(R.string.price_category_student), color = MaterialTheme.colorScheme.onBackground)
                                PriceCategory.Employee -> Text(stringResource(R.string.price_category_employee), color = MaterialTheme.colorScheme.onBackground)
                                PriceCategory.Guest -> Text(stringResource(R.string.price_category_guest), color = MaterialTheme.colorScheme.onBackground)
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(stringResource(R.string.number_of_chosen_menus) + ":", color = MaterialTheme.colorScheme.onBackground)

                            Text(numberOfChosenMenus.toString(), color = MaterialTheme.colorScheme.onBackground)
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Divider(modifier = Modifier.height(2.dp), color = MaterialTheme.colorScheme.primary)

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(stringResource(R.string.total) + ":", color = MaterialTheme.colorScheme.onBackground)

                            when (appSettings.priceCategory) {
                                PriceCategory.Student -> Text(totalPrice.studentPriceFormatted, color = MaterialTheme.colorScheme.onBackground)
                                PriceCategory.Employee -> Text(totalPrice.employeePriceFormatted, color = MaterialTheme.colorScheme.onBackground)
                                PriceCategory.Guest -> Text(totalPrice.guestPriceFormatted, color = MaterialTheme.colorScheme.onBackground)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {

                        Text(text = "* ${stringResource(R.string.payment_is_made_upon_pickup)}", fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onBackground)
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                        OutlinedButton(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceTint),
                            onClick = onCancel,
                            modifier = Modifier.fillMaxWidth(0.47F)
                        ) {

                            Icon(
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                imageVector = Icons.Filled.Cancel,
                                tint = MaterialTheme.colorScheme.surfaceTint,
                                contentDescription = stringResource(R.string.cancel),
                            )

                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

                            Text(stringResource(R.string.cancel))
                        }

                        Button(
                            onClick = onOrder,
                            enabled = numberOfChosenMenus > 0,
                            modifier = Modifier.fillMaxWidth(0.8868F)
                        ) {

                            if (orderingInProgress) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(ButtonDefaults.IconSize),
                                    color = MaterialTheme.colorScheme.background,
                                )
                            } else if (orderingCompleted && orderingWasSuccessful) {
                                Icon(
                                    modifier = Modifier.size(ButtonDefaults.IconSize),
                                    imageVector = Icons.Filled.CheckCircle,
                                    tint = MaterialTheme.colorScheme.background,
                                    contentDescription = stringResource(R.string.successful),
                                )
                            } else {
                                Icon(
                                    modifier = Modifier.size(ButtonDefaults.IconSize),
                                    imageVector = Icons.Filled.Send,
                                    tint = MaterialTheme.colorScheme.background,
                                    contentDescription = stringResource(R.string.order),
                                )
                            }

                            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

                            Text(stringResource(R.string.order))
                        }
                    }
                }
            }
        }
    }
}
