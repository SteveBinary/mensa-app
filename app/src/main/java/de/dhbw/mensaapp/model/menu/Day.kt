package de.dhbw.mensaapp.model.menu

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import de.dhbw.mensaapp.R
import kotlinx.parcelize.Parcelize
import java.time.DateTimeException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Parcelize
data class Day(val date: LocalDate) : Comparable<Day>, Parcelable {

    @Composable
    fun translatedName(): String = when (date.dayOfWeek) {
        DayOfWeek.MONDAY -> stringResource(R.string.monday)
        DayOfWeek.TUESDAY -> stringResource(R.string.tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(R.string.wednesday)
        DayOfWeek.THURSDAY -> stringResource(R.string.thursday)
        DayOfWeek.FRIDAY -> stringResource(R.string.friday)
        DayOfWeek.SATURDAY -> stringResource(R.string.saturday)
        DayOfWeek.SUNDAY -> stringResource(R.string.sunday)
        else -> stringResource(R.string.error)
    }

    @Composable
    fun formattedString(): String = try {
        date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    } catch (e: DateTimeException) {
        stringResource(R.string.error)
    }

    override fun compareTo(other: Day) = date.compareTo(other.date)
}
