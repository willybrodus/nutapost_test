package com.nutapos.nutatest.core.domain.usecase.cash_in

import com.nutapos.nutatest.core.data.repository.CashInflowRepository
import com.nutapos.nutatest.core.data.repository.CustomerRepository
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.domain.model.cash_in.CashInflowReportItem
import com.nutapos.nutatest.core.domain.model.cash_in.DailyCashInflowReport
import com.nutapos.nutatest.core.utils.ext.toRupiahFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCashInflowReportUseCase @Inject constructor(
    private val cashInflowRepository: CashInflowRepository,
    private val userRepository: UserRepository,
    private val customerRepository: CustomerRepository
) {
    /**
     * Invokes the use case to get a report of all cash inflows, without a date filter.
     */
    operator fun invoke(): Flow<List<DailyCashInflowReport>> {
        return getReport(null, null)
    }

    /**
     * Invokes the use case to get a report of cash inflows within a specific date range.
     */
    operator fun invoke(startDate: Long, endDate: Long): Flow<List<DailyCashInflowReport>> {
        return getReport(startDate, endDate)
    }

    private fun getReport(startDate: Long?, endDate: Long?): Flow<List<DailyCashInflowReport>> {
        return combine(
            cashInflowRepository.getAllCashInflows(),
            userRepository.getAllUsers(),
            customerRepository.getAllCustomers()
        ) { allInflows, allUsers, allCustomers ->
            // Apply date filter only if start and end dates are provided
            val filteredInflows = if (startDate != null && endDate != null) {
                allInflows.filter { it.createdAt in startDate..endDate }
            } else {
                allInflows
            }

            if (filteredInflows.isEmpty()) {
                return@combine emptyList<DailyCashInflowReport>()
            }

            val userMap = allUsers.associateBy { it.id }
            val customerMap = allCustomers.associateBy { it.id }

            // Group by the start of the day for accurate grouping across years
            val groupedByDay = filteredInflows.groupBy {
                val cal = Calendar.getInstance()
                cal.timeInMillis = it.createdAt
                cal.set(Calendar.HOUR_OF_DAY, 0)
                cal.set(Calendar.MINUTE, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
                cal.timeInMillis
            }

            // Sort by date descending before mapping
            groupedByDay.entries
                .sortedByDescending { it.key }
                .map { (_, dailyInflows) ->
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
                }
        }
    }
}
