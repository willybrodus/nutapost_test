package com.nutapos.nutatest.core.domain.usecase.cash_out

import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.cash_out.CashOutflowFormState
import com.nutapos.nutatest.core.domain.model.cash_out.toDomainModel
import com.nutapos.nutatest.core.domain.model.cash_out.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InsertCashOutUseCase @Inject constructor(private val repository: CashOutRepository) {
    suspend operator fun invoke(formState: CashOutflowFormState, userId: Long) = 
        repository.insertCashOut(formState.toEntity(userId))
}

class UpdateCashOutUseCase @Inject constructor(private val repository: CashOutRepository) {
    suspend operator fun invoke(formState: CashOutflowFormState, userId: Long) = 
        repository.updateCashOut(formState.toEntity(userId))
}

class GetCashOutByIdUseCase @Inject constructor(private val repository: CashOutRepository) {
    operator fun invoke(id: Long): Flow<CashOut?> = 
        repository.getCashOutById(id).map { it?.toDomainModel() }
}
