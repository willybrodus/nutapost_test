package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.DisabledBg
import com.nutapos.nutatest.core.ui.theme.DisabledText
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.SuccessLoadingBg
import com.nutapos.nutatest.core.ui.theme.White

@Composable
fun NutaTestButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val buttonEnabled = enabled && !isLoading

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = buttonEnabled,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isLoading) SuccessLoadingBg else GreenMain,
            contentColor = White,
            disabledContainerColor = if (isLoading) SuccessLoadingBg else DisabledBg,
            disabledContentColor = DisabledText
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = GreenMain,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutaTestButtonEnabledPreview() {
    NutaTestTheme {
        NutaTestButton(
            text = "Terapkan",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutaTestButtonDisabledPreview() {
    NutaTestTheme {
        NutaTestButton(
            text = "Simpan",
            onClick = {},
            enabled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutaTestButtonLoadingPreview() {
    NutaTestTheme {
        NutaTestButton(
            text = "Simpan",
            onClick = {},
            isLoading = true
        )
    }
}
