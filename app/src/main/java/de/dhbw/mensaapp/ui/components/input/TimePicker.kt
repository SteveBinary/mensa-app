package de.dhbw.mensaapp.ui.components.input

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.dhbw.mensaapp.model.menu.TimeSlot


@Composable
fun TimePicker(selectedTimeSlot: TimeSlot?, onSelect: (TimeSlot) -> Unit) {
    Column {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {

            InformationChip(
                text = TimeSlot._11_30.formattedString,
                highlightColor = selectedTimeSlot == TimeSlot._11_30,
                modifier = Modifier.width(80.dp),
                enabled = true,
                onClick = { onSelect(TimeSlot._11_30) }
            )

            Spacer(modifier = Modifier.width(15.dp))

            InformationChip(
                text = TimeSlot._12_00.formattedString,
                highlightColor = selectedTimeSlot == TimeSlot._12_00,
                modifier = Modifier.width(80.dp),
                enabled = true,
                onClick = { onSelect(TimeSlot._12_00) }
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {

            InformationChip(
                text = TimeSlot._12_30.formattedString,
                highlightColor = selectedTimeSlot == TimeSlot._12_30,
                modifier = Modifier.width(80.dp),
                enabled = true,
                onClick = { onSelect(TimeSlot._12_30) }
            )

            Spacer(modifier = Modifier.width(15.dp))

            InformationChip(
                text = TimeSlot._13_00.formattedString,
                highlightColor = selectedTimeSlot == TimeSlot._13_00,
                modifier = Modifier.width(80.dp),
                enabled = true,
                onClick = { onSelect(TimeSlot._13_00) }
            )
        }
    }
}
