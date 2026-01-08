package com.nutapos.nutatest.core.data.repository

import com.nutapos.nutatest.core.local.dao.CashInflowDao
import com.nutapos.nutatest.core.local.entity.CashInflow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CashInflowRepository {
    fun getAllCashInflows(): Flow<List<CashInflow>>
    fun getCashInflowById(id: Long): Flow<CashInflow?>
    suspend fun insertCashInflow(cashInflow: CashInflow)
    suspend fun updateCashInflow(cashInflow: CashInflow)
    suspend fun deleteCashInflow(cashInflow: CashInflow)
}

class CashInflowRepositoryImpl @Inject constructor(
    private val cashInflowDao: CashInflowDao
) : CashInflowRepository {
    override fun getAllCashInflows(): Flow<List<CashInflow>> = cashInflowDao.getAllCashInflows()
    override fun getCashInflowById(id: Long): Flow<CashInflow?> = cashInflowDao.getCashInflowById(id)
    override suspend fun insertCashInflow(cashInflow: CashInflow) = cashInflowDao.insertCashInflow(cashInflow)
    override suspend fun updateCashInflow(cashInflow: CashInflow) = cashInflowDao.updateCashInflow(cashInflow)
    override suspend fun deleteCashInflow(cashInflow: CashInflow) = cashInflowDao.deleteCashInflow(cashInflow)
}
