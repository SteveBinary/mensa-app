package de.dhbw.mensaapp.model.menu

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalTime


@Parcelize
enum class TimeSlot(val time: LocalTime) : Parcelable {
    @Suppress("Warnings")
    _11_30(LocalTime.of(11, 30)),

    @Suppress("Warnings")
    _12_00(LocalTime.of(12, 0)),

    @Suppress("Warnings")
    _12_30(LocalTime.of(12, 30)),

    @Suppress("Warnings")
    _13_00(LocalTime.of(13, 0));

    @IgnoredOnParcel
    val formattedString = "${time.hour}:${String.format("%02d", time.minute)}"
}
