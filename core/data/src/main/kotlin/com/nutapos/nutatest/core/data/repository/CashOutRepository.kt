package com.nutapos.nutatest.core.data.repository

import com.nutapos.nutatest.core.local.dao.CashOutDao
import com.nutapos.nutatest.core.local.entity.CashOut
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CashOutRepository {
    fun getAllCashOuts(): Flow<List<CashOut>>
    fun getCashOutById(id: Long): Flow<CashOut?>
    suspend fun insertCashOut(cashOut: CashOut)
    suspend fun updateCashOut(cashOut: CashOut)
    suspend fun deleteCashOut(cashOut: CashOut)
}

class CashOutRepositoryImpl @Inject constructor(
    private val cashOutDao: CashOutDao
) : CashOutRepository {
    override fun getAllCashOuts(): Flow<List<CashOut>> = cashOutDao.getAllCashOuts()
    override fun getCashOutById(id: Long): Flow<CashOut?> = cashOutDao.getCashOutById(id)
    override suspend fun insertCashOut(cashOut: CashOut) = cashOutDao.insertCashOut(cashOut)
    override suspend fun updateCashOut(cashOut: CashOut) = cashOutDao.updateCashOut(cashOut)
    override suspend fun deleteCashOut(cashOut: CashOut) = cashOutDao.deleteCashOut(cashOut)
}
