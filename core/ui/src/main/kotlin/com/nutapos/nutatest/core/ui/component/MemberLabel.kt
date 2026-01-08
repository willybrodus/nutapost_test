package com.nutapos.nutatest.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.InfoBorder
import com.nutapos.nutatest.core.ui.theme.InfoSubtleBg
import com.nutapos.nutatest.core.ui.theme.InfoText
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme

@Composable
fun MemberLabel(
  text: String,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    color = InfoSubtleBg,
    border = BorderStroke(1.dp, InfoBorder)
  ) {
    Text(
      text = text,
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
      color = InfoText,
      style = MaterialTheme.typography.labelLarge.copy(
        fontWeight = FontWeight.Medium
      )
    )
  }
}

@Preview
@Composable
fun MemberLabelPreview() {
  NutaTestTheme {
    MemberLabel("Member")
  }
}
