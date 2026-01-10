package com.nutapos.nutatest.feature.customers.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.domain.model.customer.Customer
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestTextField
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.GreenMain
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.core.ui.theme.Text4
import com.nutapos.nutatest.feature.customers.R

@Composable
fun CustomerFormScreen(
    customer: Customer?,
    isLoading: Boolean,
    onNavigateBack: () -> Unit,
    onSaveCustomer: (String, String?, String?, Boolean) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf<String?>("") }
    var email by remember { mutableStateOf<String?>("") }
    var isMember by remember { mutableStateOf(true) }

    LaunchedEffect(customer) {
        customer?.let {
            name = it.name
            phoneNumber = it.phoneNumber
            email = it.email
            isMember = it.isMember
        }
    }

    Scaffold(
        topBar = {
            NutaTestTopAppBar(
                title = stringResource(id = if (customer == null) R.string.title_add_customer else R.string.title_edit_customer),
                onNavigationClick = onNavigateBack
            )
        },
        bottomBar = {
            NutaTestButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(id = R.string.action_save),
                onClick = { onSaveCustomer(name, phoneNumber, email, isMember) },
                isLoading = isLoading
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.label_detail_customer), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            NutaTestTextField(
                value = name,
                onValueChange = { name = it },
                label = stringResource(id = R.string.label_customer_name),
                placeholder = stringResource(id = R.string.hint_customer_name)
            )
            Spacer(modifier = Modifier.height(16.dp))
            NutaTestTextField(
                value = phoneNumber ?: "",
                onValueChange = { phoneNumber = it },
                label = stringResource(id = R.string.label_phone_number),
                placeholder = stringResource(id = R.string.hint_phone_number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            NutaTestTextField(
                value = email ?: "",
                onValueChange = { email = it },
                label = stringResource(id = R.string.label_email),
                placeholder = stringResource(id = R.string.hint_email)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(id = R.string.label_status), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                StatusRadioButton(
                    text = stringResource(id = R.string.option_member),
                    selected = isMember,
                    onClick = { isMember = true }
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                StatusRadioButton(
                    text = stringResource(id = R.string.option_non_member),
                    selected = !isMember,
                    onClick = { isMember = false }
                )
            }
        }
    }
}

@Composable
private fun StatusRadioButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = GreenMain,
                unselectedColor = Text4
            )
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerFormScreenPreview() {
    NutaTestTheme {
        CustomerFormScreen(
            customer = null,
            isLoading = false,
            onNavigateBack = {},
            onSaveCustomer = { _, _, _, _ -> }
        )
    }
}
