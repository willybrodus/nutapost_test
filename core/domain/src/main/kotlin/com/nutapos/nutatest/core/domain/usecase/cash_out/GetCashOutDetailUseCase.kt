package com.nutapos.nutatest.core.domain.usecase.cash_out

import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.cash_out.toDomainModel
import com.nutapos.nutatest.core.domain.usecase.user.GetUserByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCashOutDetailUseCase @Inject constructor(
    private val repository: CashOutRepository,
    private val getUserById: GetUserByIdUseCase
) {
    operator fun invoke(id: Long): Flow<CashOut?> {
        return repository.getCashOutById(id).flatMapLatest { cashOutEntity ->
            if (cashOutEntity == null) return@flatMapLatest kotlinx.coroutines.flow.flowOf(null)

            getUserById(cashOutEntity.userId).map { user ->
              user?.let { cashOutEntity.toDomainModel(it) }
            }
        }
    }
}
