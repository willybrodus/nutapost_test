package com.nutapos.nutatest.feature.customers.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.domain.model.Customer
import com.nutapos.nutatest.core.ui.component.NutaTestButton
import com.nutapos.nutatest.core.ui.component.NutaTestEmptyState
import com.nutapos.nutatest.core.ui.component.NutaTestTextField
import com.nutapos.nutatest.core.ui.component.NutaTestTopAppBar
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.customers.R
import com.nutapos.nutatest.feature.customers.list.components.CustomerListItem

@Composable
fun CustomerListScreen(
  onNavigateBack: () -> Unit,
  onNavigateToCreate: () -> Unit,
  onCustomerSelected: (Customer) -> Unit,
  customers: List<Customer>,
  selectedCustomer: Customer?
) {
  var searchQuery by remember { mutableStateOf("") }

  Scaffold(
    topBar = {
      NutaTestTopAppBar(
        title = stringResource(id = R.string.title_customers),
        onNavigationClick = onNavigateBack
      )
    },
    bottomBar = {
      NutaTestButton(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        text = stringResource(id = R.string.action_add_new_customer),
        onClick = onNavigateToCreate
      )
    }
  ) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      NutaTestTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = "",
        placeholder = stringResource(id = R.string.hint_search_customer),
        leadingContent = {
          Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null
          )
        },
        modifier = Modifier.padding(16.dp)
      )

      if (customers.isEmpty()) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          NutaTestEmptyState(
            title = stringResource(id = R.string.empty_customer_title),
            description = stringResource(id = R.string.empty_customer_description)
          )
        }
      } else {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
          items(customers.size) { index ->
            val customer = customers[index]
            Column(modifier = Modifier.clickable { onCustomerSelected(customer) }) {
              CustomerListItem(
                name = customer.name,
                phoneNumber = customer.phoneNumber,
                email = customer.email,
                isMember = customer.isMember,
                isSelected = customer.id == selectedCustomer?.id
              )
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CustomerListScreenPreview() {
  val dummyCustomers = listOf(
    Customer(
      id = 1,
      name = "Tatiana Rosser",
      phoneNumber = "08123456789",
      email = "tatianarosser@mail.com",
      isMember = true
    ),
    Customer(id = 2, name = "Erin Dorwart", phoneNumber = null, email = null, isMember = false),
    Customer(id = 3, name = "Corey Rosser", phoneNumber = null, email = null, isMember = false),
    Customer(id = 4, name = "Mira Workman", phoneNumber = null, email = null, isMember = true),
    Customer(id = 5, name = "Emery Kenter", phoneNumber = null, email = null, isMember = false),
  )

  NutaTestTheme {
    CustomerListScreen(
      onNavigateBack = {},
      onNavigateToCreate = {},
      onCustomerSelected = {},
      customers = dummyCustomers,
      selectedCustomer = dummyCustomers[3]
    )
  }
}

@Preview(showBackground = true)
@Composable
fun CustomerListScreenEmptyPreview() {
  NutaTestTheme {
    CustomerListScreen(
      onNavigateBack = {},
      onNavigateToCreate = {},
      onCustomerSelected = {},
      customers = emptyList(),
      selectedCustomer = null
    )
  }
}
