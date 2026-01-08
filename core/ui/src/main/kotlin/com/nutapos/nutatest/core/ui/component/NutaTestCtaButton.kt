package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.DangerMain
import com.nutapos.nutatest.core.ui.theme.DisabledBg
import com.nutapos.nutatest.core.ui.theme.DisabledText
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.SuccessSubtleBg
import com.nutapos.nutatest.core.ui.theme.White

@Composable
fun NutaTestCtaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isPrimary: Boolean = true
) {
    val containerColor = if (isPrimary) SuccessSubtleBg else DangerMain
    val contentColor = if (isPrimary) GreenMain else White

    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        enabled = enabled,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = DisabledBg,
            disabledContentColor = DisabledText
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun NutaTestOutlinedCtaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        enabled = enabled,
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, if (enabled) DangerMain else DisabledText),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (enabled) DangerMain else DisabledText
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CtaButtonPreviews() {
    NutaTestTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
            NutaTestCtaButton(text = "Atur periode", onClick = {})
            NutaTestOutlinedCtaButton(text = "Batalkan", onClick = {})
            NutaTestCtaButton(text = "Hapus", onClick = {}, isPrimary = false)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CtaButtonDisabledPreviews() {
    NutaTestTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
            NutaTestCtaButton(text = "Atur periode", onClick = {}, enabled = false)
            NutaTestOutlinedCtaButton(text = "Batalkan", onClick = {}, enabled = false)
        }
    }
}
