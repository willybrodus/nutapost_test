package com.nutapos.nutatest.core.domain.usecase

import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.domain.model.CashInflowReportItem
import com.nutapos.nutatest.core.domain.model.DailyCashInflowReport
import com.nutapos.nutatest.core.utils.ext.toRupiahFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCashInflowReportUseCase @Inject constructor(
    private val cashInflowRepository: CashInflowRepository,
    private val userRepository: UserRepository,
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(startDate: Long, endDate: Long): Flow<List<DailyCashInflowReport>> {
        return combine(
            cashInflowRepository.getAllCashInflows(),
            userRepository.getAllUsers(),
            customerRepository.getAllCustomers()
        ) { allInflows, allUsers, allCustomers ->
            val filteredInflows = allInflows.filter { it.createdAt in startDate..endDate }

            val userMap = allUsers.associateBy { it.id }
            val customerMap = allCustomers.associateBy { it.id }

            val groupedByDay = filteredInflows.groupBy {
                val cal = Calendar.getInstance()
                cal.timeInMillis = it.createdAt
                cal.get(Calendar.DAY_OF_YEAR)
            }

            groupedByDay.map { (_, dailyInflows) ->
                val firstTransaction = dailyInflows.first()
                val dailyTotal = dailyInflows.sumOf { it.amount }

                val transactions = dailyInflows.map { inflow ->
                    val user = userMap[inflow.userId]
                    val customer = customerMap[inflow.customerId]

                    CashInflowReportItem(
                        id = inflow.id,
                        time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(inflow.createdAt),
                        customerName = customer?.name ?: "N/A",
                        receivedBy = user?.name ?: "N/A",
                        description = inflow.description.ifEmpty { "-" },
                        amount = inflow.amount.toRupiahFormat()
                    )
                }

                DailyCashInflowReport(
                    formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(firstTransaction.createdAt),
                    dailyTotal = dailyTotal.toRupiahFormat(),
                    transactions = transactions
                )
            }.sortedByDescending { it.transactions.first().time } // Sort days by first transaction time
        }
    }
}
