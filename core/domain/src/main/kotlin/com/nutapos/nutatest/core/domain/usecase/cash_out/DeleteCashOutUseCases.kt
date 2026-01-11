package com.nutapos.nutatest.core.domain.usecase.cash_out

import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.cash_out.toEntity
import javax.inject.Inject

class DeleteCashOutUseCase @Inject constructor(private val repository: CashOutRepository) {
    suspend operator fun invoke(cashOut: CashOut) = repository.deleteCashOut(cashOut.toEntity())
}
