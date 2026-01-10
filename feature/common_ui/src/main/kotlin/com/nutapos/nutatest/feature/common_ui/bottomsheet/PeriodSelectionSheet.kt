package com.nutapos.nutatest.feature.common_ui.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nutapos.nutatest.core.ui.component.NutaTestBottomSheet
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestTextField
import com.nutapos.nutatest.core.ui.theme.Black
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.GreenThriddenary
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Text4
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
fun PeriodSelectionDialog(
  onDismiss: () -> Unit,
  onApply: (startDate: Long, endDate: Long) -> Unit,
  initialSelectedOption: PeriodOption = PeriodOption.Today,
  initialStartDate: Long? = null,
  initialEndDate: Long? = null,
) {
  val options =
    listOf(PeriodOption.Today, PeriodOption.Yesterday, PeriodOption.Last7Days, PeriodOption.Custom)
  var selectedOption by remember { mutableStateOf(initialSelectedOption) }

  var customStartDate by remember { mutableStateOf(initialStartDate) }
  var customEndDate by remember { mutableStateOf(initialEndDate) }
  var showDatePicker by remember { mutableStateOf(false) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = onDismiss)
        .background(Color.Black.copy(alpha = 0.6f)),
      contentAlignment = Alignment.BottomCenter
    ) {
      NutaTestBottomSheet(
        title = "Pilih Periode",
        onDismissRequest = onDismiss
      ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
          options.forEach { option ->
            Row(
              Modifier
                .fillMaxWidth()
                .selectable(
                  selected = (option == selectedOption),
                  onClick = { selectedOption = option },
                  role = Role.RadioButton
                )
                .padding(vertical = 12.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              RadioButton(
                selected = (option == selectedOption),
                onClick = null,
                colors = RadioButtonDefaults.colors(
                  selectedColor = GreenMain,
                  unselectedColor = Text4
                )
              )
              Spacer(modifier = Modifier.width(16.dp))
              Text(text = option.title)
            }
          }

          if (selectedOption == PeriodOption.Custom) {
            Spacer(modifier = Modifier.height(8.dp))
            val dateFormatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
            val dateText = if (customStartDate != null && customEndDate != null) {
              "${dateFormatter.format(Date(customStartDate!!))} - ${
                dateFormatter.format(
                  Date(
                    customEndDate!!
                  )
                )
              }"
            } else {
              ""
            }

            NutaTestTextField(
              value = dateText,
              onValueChange = {},
              readOnly = true,
              label = "Periode",
              placeholder = "Pilih rentang tanggal",
              onTextFieldClick = { showDatePicker = true }
            )
          }

          Spacer(modifier = Modifier.height(32.dp))

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
          Text("Terapkan", color = GreenMain)
        }
      },
      dismissButton = {
        TextButton(onClick = { showDatePicker = false }) {
          Text("Batal", color = GreenMain)
        }
      },
      properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
      DateRangePicker(
        state = datePickerState,
        showModeToggle = true,
        modifier = Modifier.weight(1f),
        title = {
          Text(
            text = "Awal - Akhir",
            modifier = Modifier.padding(start = 24.dp, top = 16.dp)
          )
        },
        headline = {
          val dateFormatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
          val startDate =
            datePickerState.selectedStartDateMillis?.let { dateFormatter.format(Date(it)) }
              ?: "Start date"
          val endDate =
            datePickerState.selectedEndDateMillis?.let { dateFormatter.format(Date(it)) }
              ?: "End date"
          Text(
            text = "$startDate - $endDate",
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 24.dp, top = 12.dp),
            textAlign = TextAlign.Start,
          )
        },
        colors = DatePickerDefaults.colors(
          containerColor = Color.White,
          titleContentColor = Black,
          headlineContentColor = Black,
          todayContentColor = Black,
          todayDateBorderColor = GreenMain,
          selectedDayContentColor = Color.White,
          selectedDayContainerColor = GreenMain,
          dayInSelectionRangeContainerColor = GreenThriddenary,
          dayInSelectionRangeContentColor = Color.Black,
          dateTextFieldColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GreenMain,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = GreenMain,
            cursorColor = GreenMain
          )
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PeriodSelectionDialogPreview() {
  NutaTestTheme {
    PeriodSelectionDialog(
      onDismiss = {},
      onApply = { _, _ -> }
    )
  }
}
