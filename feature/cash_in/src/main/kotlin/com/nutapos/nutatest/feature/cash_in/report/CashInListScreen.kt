package com.nutapos.nutatest.feature.cash_in.report

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.nutapos.nutatest.feature.cash_in.R
import com.nutapos.nutatest.feature.cash_in.report.components.CashInListGroup
import com.nutapos.nutatest.feature.cash_in.report.components.CashInTransaction
import com.nutapos.nutatest.feature.common_ui.bottomsheet.PeriodSelectionDialog

data class CashInGroup(val date: String, val total: String, val transactions: List<CashInTransaction>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashInListScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCreate: () -> Unit,
    groups: List<CashInGroup> = emptyList(),
) {
    var showPeriodSheet by remember { mutableStateOf(false) }
    val periodSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            NutaTestTopAppBar(
                title = stringResource(id = R.string.cash_in_title),
                onNavigationClick = onNavigateBack
            )
        },
        bottomBar = {
          NutaTestButton(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
              text = stringResource(id = R.string.cash_in_create_transaction),
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
                        title = stringResource(id = R.string.cash_in_empty_title),
                        description = stringResource(id = R.string.cash_in_empty_description)
                    )
                    NutaTestCtaButton(
                        text = stringResource(id = R.string.cash_in_set_period),
                        onClick = { showPeriodSheet = true }
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
                        .clickable { showPeriodSheet = true }
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
                        CashInListGroup(
                            date = group.date,
                            total = group.total,
                            transactions = group.transactions
                        )
                    }
                }
            }
        }
    }

    if (showPeriodSheet) {
      PeriodSelectionDialog(
            //sheetState = periodSheetState,
            onDismiss = { showPeriodSheet = false },
            onApply = { _, _ ->
                showPeriodSheet = false
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CashInListScreenEmptyPreview() {
    NutaTestTheme {
        Surface {
            CashInListScreen(
                onNavigateBack = {},
                onNavigateToCreate = {},
                groups = emptyList()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CashInListScreenWithDataPreview() {
    val dummyTransactions1 = listOf(
        CashInTransaction("08:42:56", "Miracle Westervelt", "Kasir perangkat ke-49", "Rp 260.000", "menjual kardus"),
        CashInTransaction("08:42:56", "Mira Workman", "Kasir perangkat ke-49", "Rp 150.000", "menjual kardus")
    )
    val dummyTransactions2 = listOf(
        CashInTransaction("08:42:56", "Makenna Curtis", "Kasir perangkat ke-49", "Rp 60.000", null),
        CashInTransaction("08:42:56", "Rayna Septimus", "Kasir perangkat ke-49", "Rp 15.000", "menjual kardus"),
        CashInTransaction("08:42:56", "Craig Schleifer", "Kasir perangkat ke-49", "Rp 45.000", null)
    )
    val dummyGroups = listOf(
        CashInGroup("Senin, 22 April 2024", "Rp 310.000", dummyTransactions1),
        CashInGroup("Minggu, 21 April 2024", "Rp 120.000", dummyTransactions2)
    )

    NutaTestTheme {
        Surface {
            CashInListScreen(
                onNavigateBack = {},
                onNavigateToCreate = {},
                groups = dummyGroups
            )
        }
    }
}
