package com.nutapos.nutatest.core.domain.usecase.cash_in

import com.nutapos.nutatest.core.domain.model.cash_in.CashInflowDetails
import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.utils.ext.toRupiahFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetCashInflowDetailsUseCase @Inject constructor(
  private val cashInflowRepository: CashInflowRepository,
  private val userRepository: UserRepository,
  private val customerRepository: CustomerRepository
) {
    operator fun invoke(id: Long): Flow<CashInflowDetails?> {
        val cashInflowFlow = cashInflowRepository.getCashInflowById(id)

        return cashInflowFlow.combine(userRepository.getAllUsers()) { cashInflow, users ->
            cashInflow to users
        }.combine(customerRepository.getAllCustomers()) { (cashInflow, users), customers ->
            val user = users.find { it.id == cashInflow?.userId }
            val customer = customers.find { it.id == cashInflow?.customerId }

            if (cashInflow == null) {
                null
            } else {
                val formattedDate = SimpleDateFormat("dd MMMM yyyy - HH:mm:ss", Locale.getDefault())
                    .format(cashInflow.createdAt)
                val formattedAmount = cashInflow.amount.toRupiahFormat()

                CashInflowDetails(
                    createdDateTime = formattedDate,
                    receivedIn = user?.name ?: "N/A",
                    receivedFrom = customer?.name ?: "N/A",
                    description = cashInflow.description.ifEmpty { "-" },
                    amount = formattedAmount,
                    incomeType = cashInflow.incomeType,
                    proofImageUrl = cashInflow.proof
                )
            }
        }
    }
}
