package com.nutapos.nutatest.core.domain.usecase

import com.nutapos.nutatest.core.local.entity.CashOut
import com.nutapos.nutatest.core.data.repository.CashOutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCashOutsUseCase @Inject constructor(private val cashOutRepository: CashOutRepository) {
    operator fun invoke(): Flow<List<CashOut>> = cashOutRepository.getAllCashOuts()
}

class GetCashOutByIdUseCase @Inject constructor(private val cashOutRepository: CashOutRepository) {
    operator fun invoke(id: Long): Flow<CashOut?> = cashOutRepository.getCashOutById(id)
}

class InsertCashOutUseCase @Inject constructor(private val cashOutRepository: CashOutRepository) {
    suspend operator fun invoke(cashOut: CashOut) = cashOutRepository.insertCashOut(cashOut)
}

class UpdateCashOutUseCase @Inject constructor(private val cashOutRepository: CashOutRepository) {
    suspend operator fun invoke(cashOut: CashOut) = cashOutRepository.updateCashOut(cashOut)
}

class DeleteCashOutUseCase @Inject constructor(private val cashOutRepository: CashOutRepository) {
    suspend operator fun invoke(cashOut: CashOut) = cashOutRepository.deleteCashOut(cashOut)
}
