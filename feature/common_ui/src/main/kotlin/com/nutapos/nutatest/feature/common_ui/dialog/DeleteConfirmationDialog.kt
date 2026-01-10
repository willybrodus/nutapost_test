package com.nutapos.nutatest.feature.common_ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nutapos.nutatest.core.ui.component.NutaTestCtaButton
import com.nutapos.nutatest.core.ui.component.NutaTestOutlinedCtaButton
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Typography

@Composable
fun DeleteConfirmationDialog(
  title: String,
  description: String,
  onDismissRequest: () -> Unit,
  onConfirm: () -> Unit
) {
  Dialog(onDismissRequest = onDismissRequest) {
    Surface(
      shape = RoundedCornerShape(28.dp)
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
      ) {
        Text(
          text = title,
          style = Typography.headlineSmall.copy(fontWeight = FontWeight.W500),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          text = description,
          style = Typography.bodyMedium.copy(fontWeight = FontWeight.W400),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          NutaTestOutlinedCtaButton(
            text = "Batalkan",
            onClick = onDismissRequest,
            modifier = Modifier.weight(1f),
          )
          Spacer(modifier = Modifier.width(16.dp))
          NutaTestCtaButton(
            text = "Hapus",
            onClick = onConfirm,
            modifier = Modifier.weight(1f),
            isPrimary = false

          )
        }
      }
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0x80000000)
@Composable
fun DeleteConfirmationDialogPreview() {
  NutaTestTheme {
    DeleteConfirmationDialog(
      title = "Hapus Uang Masuk",
      description = "Apakah Anda ingin menghapus detail uang masuk ini?",
      onDismissRequest = {},
      onConfirm = {}
    )
  }
}
