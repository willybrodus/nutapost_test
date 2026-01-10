package com.nutapos.nutatest.feature.cash_in.detail

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
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Typography
import com.nutapos.nutatest.feature.cash_in.R
import com.nutapos.nutatest.feature.common_ui.dialog.DeleteConfirmationDialog

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CashInDetailScreen(
  onBackClick: () -> Unit,
  onEditClick: () -> Unit,
  onDeleteClick: () -> Unit,
  proofImageUri: Uri? // URI dari gambar bukti transfer
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            title = stringResource(id = R.string.title_delete_cash_in),
            description = stringResource(id = R.string.desc_delete_cash_in),
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
        title = stringResource(id = R.string.title_cash_in_detail),
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
                .padding(vertical = 8.dp) // Memperbesar area sentuh
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
      DetailItem(label = stringResource(id = R.string.label_created_date), value = "22 April 2024 - 08:24:56")
      DetailItem(label = stringResource(id = R.string.label_cash_in_to), value = "Kasir Perangkat ke-49")
      DetailItem(label = stringResource(id = R.string.label_received_from), value = "Mira Workman")
      DetailItem(label = stringResource(id = R.string.label_description), value = "-")
      DetailItem(label = stringResource(id = R.string.label_amount), value = "Rp 150.000")
      DetailItem(label = stringResource(id = R.string.label_income_type), value = "Pendapatan Lain")

      Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.title_upload_proof), style = Typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        if (proofImageUri != null) {
          GlideImage(
            model = proofImageUri, // Langsung gunakan objek Uri di sini
            contentDescription = stringResource(id = R.string.content_description_proof_transfer),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
          )
        } else {
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
fun CashInDetailScreenPreview() {
  NutaTestTheme {
    CashInDetailScreen(
      onBackClick = {},
      onEditClick = {},
      onDeleteClick = {},
      proofImageUri = null // Beri nilai null untuk preview
    )
  }
}
