package com.nutapos.nutatest.core.domain.usecase.cash_in

import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import com.nutapos.nutatest.core.domain.model.cash_in.CashInflowFormState
import com.nutapos.nutatest.core.domain.model.cash_in.toEntity
import javax.inject.Inject

class InsertCashInUseCase @Inject constructor(private val repository: CashInflowRepository) {
    suspend operator fun invoke(formState: CashInflowFormState, userId: Long) = 
        repository.insertCashInflow(formState.toEntity(userId))
}

class UpdateCashInUseCase @Inject constructor(private val repository: CashInflowRepository) {
    suspend operator fun invoke(formState: CashInflowFormState, userId: Long) = 
        repository.updateCashInflow(formState.toEntity(userId))
}
