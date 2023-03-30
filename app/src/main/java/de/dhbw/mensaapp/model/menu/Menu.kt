package de.dhbw.mensaapp.model.menu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Menu(
    val id: String,
    val name: String,
    val subTitle: String,
    val prices: Prices,
    val foodAdditivesAndAllergens: String
) : Parcelable
