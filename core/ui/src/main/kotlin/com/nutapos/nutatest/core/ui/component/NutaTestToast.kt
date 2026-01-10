package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.ToastNegativeBackground
import com.nutapos.nutatest.core.ui.theme.ToastPositiveBackground
import com.nutapos.nutatest.core.ui.theme.White

@Composable
fun NutaTestToast(
    message: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    val backgroundColor = if (isError) ToastNegativeBackground else ToastPositiveBackground

    Surface(
        modifier = modifier,
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            color = White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun NutaTestToastPreview() {
    NutaTestTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            NutaTestToast(message = "Uang masuk berhasil disimpan.")
            NutaTestToast(message = "Terjadi kesalahan.", isError = true)
        }
    }
}
