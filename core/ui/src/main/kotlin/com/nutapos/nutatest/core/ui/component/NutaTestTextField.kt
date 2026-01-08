package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.Fill3
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.TextHint
import com.nutapos.nutatest.core.ui.theme.TextLabel
import com.nutapos.nutatest.core.ui.theme.TextValue

@Composable
fun NutaTestTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    onTextFieldClick: (() -> Unit)? = null
) {
    val isValueEmpty = value.isEmpty()

    val textToDisplay = if (isValueEmpty) placeholder.orEmpty() else value
    val textColor = if (isValueEmpty) TextHint else TextValue

    OutlinedTextField(
        value = textToDisplay,
        onValueChange = {
            val newValue = if (isValueEmpty && placeholder != null) it.lastOrNull()?.toString() ?: "" else it
            onValueChange(newValue)
        },
        modifier = modifier.fillMaxWidth(),
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = textColor),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
            )
        },
        placeholder = null,
        leadingIcon = leadingContent,
        trailingIcon = trailingContent,
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                if (onTextFieldClick != null) {
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                onTextFieldClick()
                            }
                        }
                    }
                }
            },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = TextLabel,
            focusedLabelColor = TextLabel,
            unfocusedBorderColor = Fill3,
            focusedBorderColor = GreenMain,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NutaTestTextFieldPreview() {
    NutaTestTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(16.dp)) {
                var keterangan by remember { mutableStateOf("") }
                var jenisPendapatanSelection by remember { mutableStateOf("") }

                NutaTestTextField(
                    value = keterangan,
                    onValueChange = { keterangan = it },
                    label = "Keterangan",
                    placeholder = "Contoh: Menjual kemasan kardus"
                )

                NutaTestTextField(
                    value = jenisPendapatanSelection,
                    onValueChange = {},
                    readOnly = true,
                    label = "Jenis Pendapatan",
                    placeholder = "Pilih jenis pendapatan",
                    trailingContent = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Pilih Jenis Pendapatan"
                        )
                    },
                    onTextFieldClick = {
                        jenisPendapatanSelection = if (jenisPendapatanSelection == "") "Pendapatan Usaha" else ""
                    }
                )
            }
        }
    }
}
