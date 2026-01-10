package com.nutapos.nutatest.feature.common_ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.component.NutaTestBottomSheet
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

sealed class PeriodOption(val title: String) {
    data object Today : PeriodOption("Hari ini")
    data object Yesterday : PeriodOption("Kemarin")
    data object Last7Days : PeriodOption("7 hari terakhir")
    data object Custom : PeriodOption("Pilih tanggal sendiri")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodSelectionSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onApply: (startDate: Long, endDate: Long) -> Unit,
    initialSelectedOption: PeriodOption = PeriodOption.Today,
    initialStartDate: Long? = null,
    initialEndDate: Long? = null,
) {
    val options = listOf(PeriodOption.Today, PeriodOption.Yesterday, PeriodOption.Last7Days, PeriodOption.Custom)
    var selectedOption by remember { mutableStateOf(initialSelectedOption) }

    var customStartDate by remember { mutableStateOf(initialStartDate) }
    var customEndDate by remember { mutableStateOf(initialEndDate) }
    var showDatePicker by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        NutaTestBottomSheet(
            title = "Pilih Periode",
            onDismissRequest = onDismiss
        ) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                options.forEach { option ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == selectedOption),
                                onClick = { selectedOption = option },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = null // Recommended for accessibility with screenreaders
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option.title)
                    }
                }

                if (selectedOption == PeriodOption.Custom) {
                    Spacer(modifier = Modifier.height(8.dp))
                    val dateFormatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
                    val dateText = if (customStartDate != null && customEndDate != null) {
                        "${dateFormatter.format(Date(customStartDate!!))} - ${dateFormatter.format(Date(customEndDate!!))}"
                    } else {
                        ""
                    }

                    OutlinedTextField(
                        value = dateText,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDatePicker = true },
                        label = { Text("Periode") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                NutaTestButton(
                    text = "Terapkan",
                    onClick = {
                        val (start, end) = when (selectedOption) {
                            PeriodOption.Today -> getTodayRange()
                            PeriodOption.Yesterday -> getYesterdayRange()
                            PeriodOption.Last7Days -> getLast7DaysRange()
                            PeriodOption.Custom -> customStartDate to customEndDate
                        }
                        if (start != null && end != null) {
                            onApply(start, end)
                            onDismiss()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = customStartDate,
            initialSelectedEndDateMillis = customEndDate
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    customStartDate = datePickerState.selectedStartDateMillis
                    customEndDate = datePickerState.selectedEndDateMillis
                    showDatePicker = false
                }) {
                    Text("Terapkan")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            DateRangePicker(
                state = datePickerState,
                showModeToggle = false,
                title = {
                    Text(
                        text = "Tanggal Awal - Tanggal Akhir",
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp)
                    )
                }
            )
        }
    }
}

private fun getTodayRange(): Pair<Long, Long> {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    val start = cal.timeInMillis

    cal.set(Calendar.HOUR_OF_DAY, 23)
    cal.set(Calendar.MINUTE, 59)
    cal.set(Calendar.SECOND, 59)
    cal.set(Calendar.MILLISECOND, 999)
    val end = cal.timeInMillis
    return start to end
}

private fun getYesterdayRange(): Pair<Long, Long> {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    val start = cal.timeInMillis

    cal.set(Calendar.HOUR_OF_DAY, 23)
    cal.set(Calendar.MINUTE, 59)
    cal.set(Calendar.SECOND, 59)
    cal.set(Calendar.MILLISECOND, 999)
    val end = cal.timeInMillis
    return start to end
}

private fun getLast7DaysRange(): Pair<Long, Long> {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, 23)
    cal.set(Calendar.MINUTE, 59)
    cal.set(Calendar.SECOND, 59)
    cal.set(Calendar.MILLISECOND, 999)
    val end = cal.timeInMillis

    cal.add(Calendar.DATE, -6)
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    val start = cal.timeInMillis
    return start to end
}

@Preview(showBackground = true)
@Composable
fun PeriodSelectionSheetPreview() {
    NutaTestTheme {
        NutaTestBottomSheet(
            title = "Pilih Periode",
            onDismissRequest = {}
        ) {
            // The content is complex to preview statically, 
            // but this ensures the container itself is correct.
        }
    }
}
