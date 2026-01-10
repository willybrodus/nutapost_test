package com.nutapos.nutatest.core.domain.usecase.cash_out

import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.cash_out.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCashOutDetailUseCase @Inject constructor(private val repository: CashOutRepository) {
  operator fun invoke(id: Long): Flow<CashOut?> =
    repository.getCashOutById(id).map { it?.toDomainModel() }
}