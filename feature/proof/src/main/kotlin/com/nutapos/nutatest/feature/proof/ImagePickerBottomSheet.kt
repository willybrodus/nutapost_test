package com.nutapos.nutatest.feature.proof

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nutapos.nutatest.core.ui.component.NutaTestBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerBottomSheet(
  sheetState: SheetState,
  onDismiss: () -> Unit,
  onGalleryClick: () -> Unit,
  onCameraClick: () -> Unit
) {
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState
  ) {
    NutaTestBottomSheet(
      title = "Ambil dari",
      onDismissRequest = onDismiss
    ) {
      Column {
        BottomSheetItem(
          icon = painterResource(id = R.drawable.ic_gallery),
          text = "Galeri",
          onClick = onGalleryClick
        )
        BottomSheetItem(
          icon = painterResource(id = R.drawable.ic_camera),
          text = "Kamera",
          onClick = onCameraClick
        )
      }
    }
  }
}

@Composable
private fun BottomSheetItem(icon: Painter, text: String, onClick: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(horizontal = 24.dp, vertical = 16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null)
    Spacer(modifier = Modifier.width(16.dp))
    Text(text, fontSize = 16.sp)
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      painter = painterResource(id = R.drawable.ic_arrow_right),
      contentDescription = null
    )
  }
}
