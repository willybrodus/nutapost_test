package com.nutapos.nutatest.feature.cash_out.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.DividerColor
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.TextValue

@Composable
fun CashOutListGroup(
    modifier: Modifier = Modifier,
    date: String,
    total: String,
    transactions: List<CashOutTransaction>
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        ) {
            Text(
                text = date,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                color = TextValue
            )
            Text(
                text = total,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                color = GreenMain
            )
        }

        Column {
            transactions.forEach { transaction ->
                CashOutListItem(
                    time = transaction.time,
                    cashOutFrom = transaction.cashOutFrom,
                    amount = transaction.amount,
                    description = transaction.description,
                    outcomeType = transaction.outcomeType
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 8.dp, color = DividerColor)
    }
}
