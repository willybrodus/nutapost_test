package com.nutapos.nutatest.feature.cash_in.report.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.theme.DividerColor
import com.nutapos.nutatest.core.ui.theme.NoteBackground
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.TextHint
import com.nutapos.nutatest.core.ui.theme.TextLabel
import com.nutapos.nutatest.core.ui.theme.TextValue
import com.nutapos.nutatest.feature.cash_in.R

@Composable
fun CashInListItem(
    modifier: Modifier = Modifier,
    time: String,
    name: String,
    cashier: String,
    amount: String,
    note: String?
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(DividerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cash_in),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextHint
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                        color = TextValue
                    )
                    Text(
                        text = cashier,
                        style = MaterialTheme.typography.labelLarge,
                        color = TextLabel
                    )
                }
                Text(
                    text = amount,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                    color = TextValue
                )
            }
            if (!note.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                      .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, NoteBackground, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_note),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = TextHint
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = note,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextHint
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CashInListItemPreview() {
    NutaTestTheme {
        CashInListItem(
            modifier = Modifier.padding(16.dp),
            time = "08:42:56",
            name = "Mira Workman",
            cashier = "Kasir perangkat ke-49",
            amount = "Rp 150.000",
            note = "menjual kardus"
        )
    }
}
