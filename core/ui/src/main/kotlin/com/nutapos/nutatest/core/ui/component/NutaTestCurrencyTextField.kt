package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import java.text.NumberFormat
import java.util.Locale

class CurrencyVisualTransformation(private val locale: Locale = Locale("id", "ID")) : VisualTransformation {

    private val numberFormat = NumberFormat.getNumberInstance(locale)

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        if (originalText.isEmpty()) {
            return TransformedText(text, OffsetMapping.Identity)
        }

        val cleanedText = originalText.filter { it.isDigit() }
        if (cleanedText.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        val formattedText = try {
            val number = cleanedText.toLong()
            numberFormat.format(number)
        } catch (e: NumberFormatException) {
            // Handle cases where the number is too large for a Long
            cleanedText
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val rightDigits = originalText.substring(offset).count { it.isDigit() }
                val commas = (formattedText.length - cleanedText.length) / 3
                var transformedOffset = formattedText.length - rightDigits
                for (i in 0 until commas) {
                    if (transformedOffset > (i + 1) * 3 + i) {
                        transformedOffset--
                    }
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset - formattedText.count { it == '.' }
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}

@Composable
fun NutaTestCurrencyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.filter { char -> char.isDigit() }) },
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF212426)),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
            )
        },
        leadingIcon = { Text("Rp") },
        visualTransformation = CurrencyVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Color(0xFF4B5258),
            focusedLabelColor = Color(0xFF4B5258),
            unfocusedBorderColor = Color(0xFFE4EBF5),
            focusedBorderColor = Color(0xFF3DAE2F), // Using primary color for focus
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NutaTestCurrencyTextFieldPreview() {
    NutaTestTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                var jumlah by remember { mutableStateOf("150000") }

                NutaTestCurrencyTextField(
                    value = jumlah,
                    onValueChange = { jumlah = it },
                    label = "Jumlah"
                )
            }
        }
    }
}
