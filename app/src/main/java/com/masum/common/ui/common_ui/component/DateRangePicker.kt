package com.masum.common.ui.common_ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.masum.common.ui.theme.*
import com.masum.common.utils.DateTimeUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK", color = primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = primary)
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        properties = DialogProperties(usePlatformDefaultWidth = true)
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            modifier = Modifier.padding(vertical = 16.dp),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Select date range")
                }
            },
            headline = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    (if(dateRangePickerState.selectedStartDateMillis!=null) dateRangePickerState.selectedStartDateMillis?.let { DateTimeUtils.millisToFormattedDateTime(it, DateTimeUtils.DD_MMM_YYYY_STRING) } else "Start Date")?.let { Text(text = "$it - ") }
                    (if(dateRangePickerState.selectedEndDateMillis!=null) dateRangePickerState.selectedEndDateMillis?.let { DateTimeUtils.millisToFormattedDateTime(it, DateTimeUtils.DD_MMM_YYYY_STRING) } else "End Date")?.let { Text(text = it) }
                }
            },
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = Color.Black,
                headlineContentColor = Color.Black,
                weekdayContentColor = Color.Black,
                subheadContentColor = Color.Black,
                yearContentColor = Color.Green,
                currentYearContentColor = Color.Red,
                selectedYearContainerColor = Color.Red,
                dayContentColor = Color.Black,
                disabledDayContentColor = Color.Gray,
                todayContentColor = primary,
                todayDateBorderColor = primary,
                dayInSelectionRangeContainerColor = Color.LightGray,
                dayInSelectionRangeContentColor = Color.White,
                selectedDayContainerColor = primary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateRangePickerPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val openDialog = remember { mutableStateOf(false) }
        val selectedDate = remember { mutableStateOf<Long?>(null) }

        if (selectedDate.value != null) {
            val date = Date(selectedDate.value!!)
            val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
            Text("Selected date: $formattedDate")
        } else {
            Text("No date selected")
        }

        if (openDialog.value) {
            DateRangePickerModal(
                onDateRangeSelected = {
                    selectedDate.value = it.first
                    openDialog.value = false
                },
                onDismiss = { openDialog.value = false }
            )
        }

        Button(
            shape = RoundedCornerShape(5.dp),
            onClick = {
                openDialog.value = true
            }) {
            Text(
                text = "Open Dialog",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}