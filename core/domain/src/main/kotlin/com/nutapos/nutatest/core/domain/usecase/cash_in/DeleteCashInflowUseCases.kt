package com.nutapos.nutatest.core.domain.usecase.cash_in

import com.nutapos.nutatest.core.local.entity.CashInflow
import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import javax.inject.Inject

class DeleteCashInflowUseCase @Inject constructor(private val cashInflowRepository: CashInflowRepository) {
    suspend operator fun invoke(cashInflow: CashInflow) = cashInflowRepository.deleteCashInflow(cashInflow)
}
