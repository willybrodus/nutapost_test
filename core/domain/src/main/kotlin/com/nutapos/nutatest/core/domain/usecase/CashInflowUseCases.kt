package com.nutapos.nutatest.core.domain.usecase

import com.nutapos.nutatest.core.local.entity.CashInflow
import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCashInflowsUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    operator fun invoke(): Flow<List<CashInflow>> = cashInflowRepository.getAllCashInflows()
}

class GetCashInflowByIdUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    operator fun invoke(id: Long): Flow<CashInflow?> = cashInflowRepository.getCashInflowById(id)
}

class InsertCashInflowUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    suspend operator fun invoke(cashInflow: CashInflow) = cashInflowRepository.insertCashInflow(cashInflow)
}

class UpdateCashInflowUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    suspend operator fun invoke(cashInflow: CashInflow) = cashInflowRepository.updateCashInflow(cashInflow)
}

class DeleteCashInflowUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    suspend operator fun invoke(cashInflow: CashInflow) = cashInflowRepository.deleteCashInflow(cashInflow)
}
