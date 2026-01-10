package com.nutapos.nutatest.feature.cash_out.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestCtaButton
import com.nutapos.nutatest.core.ui.component.NutaTestEmptyState
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.GreenSecondary
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.cash_out.R
import com.nutapos.nutatest.feature.cash_out.list.components.CashOutListGroup
import com.nutapos.nutatest.feature.cash_out.list.components.CashOutTransaction
import com.nutapos.nutatest.feature.common_ui.dialog.PeriodSelectionDialog

data class CashOutGroup(val date: String, val total: String, val transactions: List<CashOutTransaction>)

@Composable
fun CashOutListScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCreate: () -> Unit,
    groups: List<CashOutGroup> = emptyList(),
) {
    var showPeriodDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            NutaTestTopAppBar(
                title = stringResource(id = R.string.cash_out_title),
                onNavigationClick = onNavigateBack
            )
        },
        bottomBar = {
            NutaTestButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(id = R.string.cash_out_create_transaction),
                onClick = onNavigateToCreate
            )
        }
    ) { paddingValues ->
        if (groups.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NutaTestEmptyState(
                        title = stringResource(id = R.string.cash_out_empty_title),
                        description = stringResource(id = R.string.cash_out_empty_description)
                    )
                    NutaTestCtaButton(
                        text = stringResource(id = R.string.cash_out_set_period),
                        onClick = { showPeriodDialog = true }
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, GreenMain, RoundedCornerShape(12.dp))
                        .background(GreenSecondary)
                        .clickable { showPeriodDialog = true }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check_mark),
                        contentDescription = null,
                        tint = GreenMain
                    )
                    Text(
                        text = "16 Apr 2024 - 22 Apr 2024",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = GreenMain
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = null,
                        tint = GreenMain
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(groups) { group ->
                        CashOutListGroup(
                            date = group.date,
                            total = group.total,
                            transactions = group.transactions
                        )
                    }
                }
            }
        }
    }

    if (showPeriodDialog) {
        PeriodSelectionDialog(
            onDismiss = { showPeriodDialog = false },
            onApply = { _, _ ->
                showPeriodDialog = false
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CashOutListScreenEmptyPreview() {
    NutaTestTheme {
        Surface {
            CashOutListScreen(
                onNavigateBack = {},
                onNavigateToCreate = {},
                groups = emptyList()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CashOutListScreenWithDataPreview() {
    val dummyTransactions1 = listOf(
        CashOutTransaction("08:42:56",  "Kasir perangkat ke-49", "Rp 50.000",  "Galon Aqua", "Modal"),
        CashOutTransaction("08:42:56", "Kasir perangkat ke-49", "Rp 25.000",  "Listrik Token 25k", "Pendapatan")
    )
    val dummyTransactions2 = listOf(
        CashOutTransaction("08:42:56", "Kasir perangkat ke-49", "Rp 15.000",  "Iuran Sampah", "Modal")
    )
    val dummyGroups = listOf(
        CashOutGroup("Senin, 22 April 2024", "Rp 75.000", dummyTransactions1),
        CashOutGroup("Minggu, 21 April 2024", "Rp 15.000", dummyTransactions2)
    )

    NutaTestTheme {
        Surface {
            CashOutListScreen(
                onNavigateBack = {},
                onNavigateToCreate = {},
                groups = dummyGroups
            )
        }
    }
}
