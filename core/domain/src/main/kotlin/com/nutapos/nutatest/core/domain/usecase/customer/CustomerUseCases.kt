package com.nutapos.nutatest.core.domain.usecase.customer

import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.domain.model.customer.Customer
import com.nutapos.nutatest.core.domain.model.customer.toDomainModel
import com.nutapos.nutatest.core.domain.model.customer.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCustomersUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(): Flow<List<Customer>> = 
        customerRepository.getAllCustomers().map { entityList ->
            entityList.map { it.toDomainModel() }
        }
}

class GetCustomerByIdUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(id: Long): Flow<Customer?> = 
        customerRepository.getCustomerById(id).map { it?.toDomainModel() }
}

class InsertCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer): Long = 
        customerRepository.insertCustomer(customer.toEntity())
}

class UpdateCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = 
        customerRepository.updateCustomer(customer.toEntity())
}

class DeleteCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = 
        customerRepository.deleteCustomer(customer.toEntity())
}

class SearchCustomersUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    operator fun invoke(query: String): Flow<List<Customer>> {
        return customerRepository.getAllCustomers().map { customers ->
            val domainCustomers = customers.map { it.toDomainModel() }
            if (query.isBlank()) {
                domainCustomers
            } else {
                domainCustomers.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}
