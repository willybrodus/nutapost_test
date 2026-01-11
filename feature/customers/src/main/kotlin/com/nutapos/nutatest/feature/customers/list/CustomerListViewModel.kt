package com.nutapos.nutatest.feature.customers.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.customer.Customer
import com.nutapos.nutatest.core.domain.usecase.customer.GetAllCustomersUseCase
import com.nutapos.nutatest.core.domain.usecase.customer.GetCustomerByIdUseCase
import com.nutapos.nutatest.core.domain.usecase.customer.SearchCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val getAllCustomersUseCase: GetAllCustomersUseCase,
    private val searchCustomersUseCase: SearchCustomersUseCase,
    private val getCustomerByIdUseCase: GetCustomerByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CustomerListState())
    val uiState: StateFlow<CustomerListState> = _uiState.asStateFlow()

    init {
        getAllCustomers()
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchCustomersUseCase(query).onEach { customers ->
            _uiState.update { it.copy(customers = customers) }
        }.launchIn(viewModelScope)
    }

    fun onCustomerSelected(customer: Customer) {
        _uiState.update { it.copy(selectedCustomer = customer) }
    }

    fun onNewCustomerCreated(customerId: Long) {
        viewModelScope.launch {
            getAllCustomers()
            val newCustomer = getCustomerByIdUseCase(customerId).first()
            _uiState.update { it.copy(selectedCustomer = newCustomer) }
        }
    }

    private fun getAllCustomers() {
        getAllCustomersUseCase().onEach { customers ->
            _uiState.update { it.copy(customers = customers) }
        }.launchIn(viewModelScope)
    }
}

data class CustomerListState(
    val customers: List<Customer> = emptyList(),
    val searchQuery: String = "",
    val selectedCustomer: Customer? = null
)
