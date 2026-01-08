package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme

@Composable
fun NutaTestDialog(
    onDismissRequest: () -> Unit,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    dismissButton: @Composable RowScope.() -> Unit,
    confirmButton: @Composable RowScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 24.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    dismissButton()
                    confirmButton()
                }
            }
        }
    }
}

@Preview
@Composable
fun NutaTestDialogPreview() {
    NutaTestTheme {
        NutaTestDialog(
            onDismissRequest = {},
            title = "Hapus Uang Masuk",
            description = "Apakah Anda ingin menghapus detail uang masuk ini?",
            dismissButton = {
                NutaTestOutlinedCtaButton(
                    text = "Batalkan",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            },
            confirmButton = {
                NutaTestCtaButton(
                    text = "Hapus",
                    onClick = {},
                    isPrimary = false,
                    modifier = Modifier.weight(1f)
                )
            }
        )
    }
}
