package com.nutapos.nutatest.feature.cash_in.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.component.NutaTestBottomSheet
import com.nutapos.nutatest.core.ui.theme.DialogDividerColor
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.TextValue

sealed class IncomeType(val title: String, val description: String, val example: String) {
    data object OtherIncome : IncomeType(
        title = "Pendapatan Lain",
        description = "Uang yang masuk ke laci kasir atau rekening yang menambahkan profit atau laba bisnis.",
        example = "Contoh:\nMenjual kemasan kardus."
    )

    data object NonIncome : IncomeType(
        title = "Non Pendapatan",
        description = "Uang yang masuk ke laci kasir atau rekening yang TIDAK menambahkan profit atau laba bisnis.",
        example = "Contoh:\nModal awal untuk kembalian ke pelanggan."
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeTypeSelectionBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onSelected: (IncomeType) -> Unit,
    initialSelected: IncomeType? = null
) {
    val options = listOf(IncomeType.OtherIncome, IncomeType.NonIncome)
    var selectedOption by remember { mutableStateOf(initialSelected) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        NutaTestBottomSheet(
            title = "Pilih Jenis Pendapatan",
            onDismissRequest = onDismiss
        ) {
            Column {
                options.forEachIndexed { index, option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == selectedOption),
                                onClick = { 
                                    selectedOption = option
                                    onSelected(option)
                                 },
                                role = Role.RadioButton
                            )
                            .padding(24.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = option.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                                color = TextValue
                            )
                            Text(
                                text = option.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextValue,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = option.example,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextValue,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = null
                        )
                    }
                    if (index < options.lastIndex) {
                        HorizontalDivider(color = DialogDividerColor)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IncomeTypeSelectionBottomSheetPreview() {
    NutaTestTheme {
        NutaTestBottomSheet(
            title = "Pilih Jenis Pendapatan",
            onDismissRequest = {}
        ) {
            val options = listOf(IncomeType.OtherIncome, IncomeType.NonIncome)
            var selectedOption by remember { mutableStateOf<IncomeType?>(IncomeType.OtherIncome) }
            Column {
                options.forEachIndexed { index, option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == selectedOption),
                                onClick = { selectedOption = option },
                                role = Role.RadioButton
                            )
                            .padding(24.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = option.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                                color = TextValue
                            )
                            Text(
                                text = option.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextValue,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = option.example,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextValue,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = null
                        )
                    }
                    if (index < options.lastIndex) {
                        HorizontalDivider(color = DialogDividerColor)
                    }
                }
            }
        }
    }
}
