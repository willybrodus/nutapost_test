package com.nutapos.nutatest.feature.cash_out.detail

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Typography
import com.nutapos.nutatest.feature.cash_out.R
import com.nutapos.nutatest.feature.common_ui.dialog.DeleteConfirmationDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CashOutDetailScreen(
    cashOut: CashOut,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            title = stringResource(id = R.string.title_delete_cash_out),
            description = stringResource(id = R.string.desc_delete_cash_out),
            onDismissRequest = { showDeleteDialog = false },
            onConfirm = {
                showDeleteDialog = false
                onDeleteClick()
            }
        )
    }

    Scaffold(
        topBar = {
            NutaTestTopAppBar(
                title = stringResource(id = R.string.title_cash_out_detail),
                onNavigationClick = onBackClick
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)) {
                NutaTestButton(
                    text = stringResource(id = R.string.action_edit_transaction),
                    onClick = onEditClick,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDeleteDialog = true }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.action_delete),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = Typography.labelLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.W500),
                        color = Color(0xFFFF3553),
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DetailItem(
                label = stringResource(id = R.string.label_created_date),
                value = SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.getDefault()).format(Date(cashOut.createdAt))
            )
            DetailItem(
                label = stringResource(id = R.string.label_cash_out_from),
                value = cashOut.user.name
            )
            DetailItem(label = stringResource(id = R.string.label_description), value = cashOut.note)
            DetailItem(label = stringResource(id = R.string.label_amount), value = cashOut.amount.toString())
            DetailItem(
                label = stringResource(id = R.string.label_source_outcome_type),
                value = cashOut.sourceOutcomeType
            )

            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.title_upload_proof),
                    style = Typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                cashOut.proof?.let {
                    GlideImage(
                        model = Uri.parse(it),
                        contentDescription = stringResource(id = R.string.content_description_proof_transfer),
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                } ?: run {
                    Text(text = stringResource(id = R.string.text_no_proof_transfer))
                }
            }
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = Typography.labelLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.W400)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = Typography.titleMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.W500)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CashOutDetailScreenPreview() {
    NutaTestTheme {
        CashOutDetailScreen(
            cashOut = CashOut(
                id = 1,
                createdAt = System.currentTimeMillis(),
                user = User(1, "John Doe"),
                note = "Beli Aqua",
                amount = 50000.0,
                sourceOutcomeType = "Modal",
                proof = null
            ),
            onBackClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}
