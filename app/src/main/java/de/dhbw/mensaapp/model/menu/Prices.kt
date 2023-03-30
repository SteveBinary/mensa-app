package de.dhbw.mensaapp.model.menu

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


@Parcelize
data class Prices(
    val unit: String,
    val student: Float,
    val employee: Float,
    val guest: Float,
) : Comparable<Prices>, Parcelable {

    companion object {
        @IgnoredOnParcel
        const val defaultUnit = "€"
    }

    private fun Float.format(digits: Int) = "%.${digits}f".format(this)

    @IgnoredOnParcel
    val studentPriceFormatted = "${student.format(2).replace(".", ",").trim()}$unit"

    @IgnoredOnParcel
    val employeePriceFormatted = "${employee.format(2).replace(".", ",").trim()}$unit"

    @IgnoredOnParcel
    val guestPriceFormatted = "${guest.format(2).replace(".", ",").trim()}$unit"


    operator fun times(factor: Float): Prices {
        return Prices(
            unit = this.unit,
            student = this.student * factor,
            employee = this.employee * factor,
            guest = this.guest * factor,
        )
    }

    operator fun plus(other: Prices): Prices {
        return Prices(
            unit = this.unit,
            student = this.student + other.student,
            employee = this.employee + other.employee,
            guest = this.guest + other.guest,
        )
    }

    override fun compareTo(other: Prices) = compareValuesBy(this, other, { it.student }, { it.employee }, { it.guest })
}

fun List<Prices>.sum(defaultUnit: String = ""): Prices {
    if (this.isEmpty()) return Prices(defaultUnit, 0F, 0F, 0F)

    var result = Prices(this[0].unit, 0F, 0F, 0F)

    this.forEach {
        result += it
    }

    return result
}
