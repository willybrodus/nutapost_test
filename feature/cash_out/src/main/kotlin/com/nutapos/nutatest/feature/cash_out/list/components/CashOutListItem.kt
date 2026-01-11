package com.nutapos.nutatest.feature.cash_out.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.Text4
import com.nutapos.nutatest.feature.cash_out.R

@Composable
fun CashOutListItem(
  time: String,
  sourceOutcomeType: String,
  cashOutFrom: String,
  amount: String,
  description: String?,
  onClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 12.dp)
      .clickable(onClick = onClick)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        painter = painterResource(id = R.drawable.ic_cash_out),
        contentDescription = null,
        tint = Text4
      )
      Column(modifier = Modifier
        .padding(horizontal = 8.dp)
        .weight(1f)) {
        Text(text = time, style = MaterialTheme.typography.bodySmall, color = Text4)
        Text(
          text = sourceOutcomeType,
          style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
        )
        Text(text = description ?: "", style = MaterialTheme.typography.bodySmall, color = Text4)
      }
      Column(horizontalAlignment = Alignment.End) {
        Text(
          text = amount,
          style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
        )
        Text(text = cashOutFrom, style = MaterialTheme.typography.bodySmall, color = Text4)
      }
    }
  }
}
