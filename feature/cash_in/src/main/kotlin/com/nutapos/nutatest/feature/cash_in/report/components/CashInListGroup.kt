package com.nutapos.nutatest.feature.cash_in.report.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.DividerColor
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.TextValue

// A dummy data class for preview purposes
data class CashInTransaction(
    val time: String,
    val name: String,
    val cashier: String,
    val amount: String,
    val note: String?
)

@Composable
fun CashInListGroup(
    modifier: Modifier = Modifier,
    date: String,
    total: String,
    transactions: List<CashInTransaction>
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
                CashInListItem(
                    time = transaction.time,
                    name = transaction.name,
                    cashier = transaction.cashier,
                    amount = transaction.amount,
                    note = transaction.note
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 8.dp, color = DividerColor)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CashInListGroupPreview() {
    val dummyTransactions = listOf(
        CashInTransaction("08:42:56", "Miracle Westervelt", "Kasir perangkat ke-49", "Rp 260.000", "menjual kardus"),
        CashInTransaction("08:42:56", "Mira Workman", "Kasir perangkat ke-49", "Rp 150.000", "menjual kardus")
    )
    NutaTestTheme {
        CashInListGroup(
            modifier = Modifier.padding(16.dp),
            date = "Senin, 22 April 2024",
            total = "Rp 310.000",
            transactions = dummyTransactions
        )
    }
}
