package com.nutapos.nutatest.feature.customers.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.customer.Customer
import com.nutapos.nutatest.core.domain.usecase.customer.GetCustomerByIdUseCase
import com.nutapos.nutatest.core.domain.usecase.customer.InsertCustomerUseCase
import com.nutapos.nutatest.core.domain.usecase.customer.UpdateCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerFormViewModel @Inject constructor(
    private val getCustomerByIdUseCase: GetCustomerByIdUseCase,
    private val insertCustomerUseCase: InsertCustomerUseCase,
    private val updateCustomerUseCase: UpdateCustomerUseCase
) : ViewModel() {

    private val _customer = MutableStateFlow<Customer?>(null)
    val customer = _customer.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _showToast = MutableSharedFlow<String>()
    val showToast = _showToast.asSharedFlow()

    private val _createdCustomerId = MutableSharedFlow<Long?>()
    val createdCustomerId = _createdCustomerId.asSharedFlow()

    fun loadCustomer(customerId: Long) {
        viewModelScope.launch {
            getCustomerByIdUseCase(customerId).collect {
                _customer.value = it
            }
        }
    }

    fun createCustomer(
        name: String,
        phoneNumber: String?,
        email: String?,
        isMember: Boolean
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val customer = Customer(
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    isMember = isMember
                )
                val newCustomerId = insertCustomerUseCase(customer)
                _createdCustomerId.emit(newCustomerId)
                _showToast.emit("berhasil disimpan")
            } catch (e: Exception) {
                _showToast.emit("Gagal menyimpan: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateCustomer(
        id: Long,
        name: String,
        phoneNumber: String?,
        email: String?,
        isMember: Boolean
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val customer = Customer(
                    id = id,
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    isMember = isMember
                )
                updateCustomerUseCase(customer)
                _showToast.emit("berhasil diupdate")
            } catch (e: Exception) {
                _showToast.emit("Gagal mengupdate: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
