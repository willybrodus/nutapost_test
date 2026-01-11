package com.nutapos.nutatest.core.data.repository

import com.nutapos.nutatest.core.local.dao.CustomerDao
import com.nutapos.nutatest.core.local.entity.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CustomerRepository {
    fun getAllCustomers(): Flow<List<Customer>>
    fun getCustomerById(id: Long): Flow<Customer?>
    suspend fun insertCustomer(customer: Customer): Long
    suspend fun updateCustomer(customer: Customer)
    suspend fun deleteCustomer(customer: Customer)
}

class CustomerRepositoryImpl @Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAllCustomers()
    override fun getCustomerById(id: Long): Flow<Customer?> = customerDao.getCustomerById(id)
    override suspend fun insertCustomer(customer: Customer): Long = customerDao.insertCustomer(customer)
    override suspend fun updateCustomer(customer: Customer) = customerDao.updateCustomer(customer)
    override suspend fun deleteCustomer(customer: Customer) = customerDao.deleteCustomer(customer)
}
