package com.nutapos.nutatest.core.domain.usecase

import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.local.entity.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCustomersUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(): Flow<List<Customer>> = customerRepository.getAllCustomers()
}

class GetCustomerByIdUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(id: Long): Flow<Customer?> = customerRepository.getCustomerById(id)
}

class InsertCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = customerRepository.insertCustomer(customer)
}

class UpdateCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = customerRepository.updateCustomer(customer)
}

class DeleteCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = customerRepository.deleteCustomer(customer)
}

class SearchCustomersUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(query: String): Flow<List<Customer>> {
        return customerRepository.getAllCustomers().map { customers ->
            if (query.isBlank()) {
                customers
            } else {
                customers.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}
