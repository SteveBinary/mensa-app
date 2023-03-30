package de.dhbw.mensaapp.ui.components.menu

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.R
import de.dhbw.mensaapp.model.AppSettings
import de.dhbw.mensaapp.model.PriceCategory
import de.dhbw.mensaapp.model.menu.Menu
import de.dhbw.mensaapp.model.menu.MenuOrderOptions
import de.dhbw.mensaapp.model.menu.TimeSlot
import de.dhbw.mensaapp.ui.components.input.InformationChip
import de.dhbw.mensaapp.ui.components.input.NumberSpinner
import de.dhbw.mensaapp.ui.components.input.TimePicker


@Composable
fun Menu(
    menu: Menu,
    menuOrderOptions: MenuOrderOptions,
    appSettings: AppSettings,
    onUpdateOrderCount: (Int) -> Unit,
    onUpdateTime: (TimeSlot?) -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable { isExpanded = !isExpanded }
            .shadow(
                elevation = 3.dp,
                spotColor = MaterialTheme.colorScheme.onBackground,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp, end = 10.dp, bottom = 15.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                MenuBriefInformation(
                    modifier = Modifier.fillMaxWidth(0.7F),
                    appSettings = appSettings,
                    numberChosen = menuOrderOptions.second,
                    menu = menu,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {

                    val context = LocalContext.current
                    val errorText = stringResource(R.string.you_must_choose_a_time)
                    NumberSpinner(value = menuOrderOptions.second, minValue = 0, maxValue = 20) { newOrderCount ->
                        if (menuOrderOptions.first == null) {
                            isExpanded = true
                            Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                        } else if (newOrderCount == 0) {
                            onUpdateTime(null)
                            onUpdateOrderCount(newOrderCount)
                        } else {
                            onUpdateOrderCount(newOrderCount)
                        }
                    }
                }
            }

            if (isExpanded) {
                MenuAdditionalInformation(
                    menu = menu,
                    showFoodAdditivesAndAllergens = appSettings.showFoodAdditivesAndAllergens,
                    selectedTimeSlot = menuOrderOptions.first,
                    onSelectTime = { newTime ->
                        if (menuOrderOptions.second == 0) {
                            onUpdateOrderCount(1)
                        }

                        onUpdateTime(newTime)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun MenuBriefInformation(modifier: Modifier, appSettings: AppSettings, numberChosen: Int, menu: Menu) {
    Column(modifier) {

        Text(text = menu.name, style = MaterialTheme.typography.titleMedium.copy(hyphens = Hyphens.Auto), color = MaterialTheme.colorScheme.onBackground)

        if (menu.subTitle.isNotBlank()) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = menu.subTitle, style = MaterialTheme.typography.titleSmall.copy(hyphens = Hyphens.Auto), color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            val mutedColor = numberChosen == 0
            val prices = menu.prices * maxOf(1, numberChosen).toFloat()
            val priceToShow = when (appSettings.priceCategory) {
                PriceCategory.Student -> prices.studentPriceFormatted
                PriceCategory.Employee -> prices.employeePriceFormatted
                PriceCategory.Guest -> prices.guestPriceFormatted
            }

            InformationChip(
                text = priceToShow,
                highlightColor = !mutedColor,
                modifier = Modifier.width(80.dp),
            )
        }
    }
}


@Composable
fun MenuAdditionalInformation(menu: Menu, showFoodAdditivesAndAllergens: Boolean, selectedTimeSlot: TimeSlot?, onSelectTime: (TimeSlot) -> Unit) {
    TimePicker(selectedTimeSlot = selectedTimeSlot, onSelect = onSelectTime)

    if (showFoodAdditivesAndAllergens && menu.foodAdditivesAndAllergens.isNotBlank()) {
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = menu.foodAdditivesAndAllergens, color = MaterialTheme.colorScheme.onBackground)
    }
}
