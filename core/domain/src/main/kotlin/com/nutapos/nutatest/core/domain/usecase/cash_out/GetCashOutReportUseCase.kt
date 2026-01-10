package com.nutapos.nutatest.core.domain.usecase.cash_out

import com.nutapos.nutatest.core.data.repository.CashOutRepository
import com.nutapos.nutatest.core.data.repository.UserRepository
import com.nutapos.nutatest.core.domain.model.cash_out.CashOutReportItem
import com.nutapos.nutatest.core.domain.model.cash_out.DailyCashOutReport
import com.nutapos.nutatest.core.utils.ext.toRupiahFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCashOutReportUseCase @Inject constructor(
    private val cashOutRepository: CashOutRepository,
    private val userRepository: UserRepository
) {
    /**
     * Invokes the use case to get a report of all cash outflows, without a date filter.
     */
    operator fun invoke(): Flow<List<DailyCashOutReport>> {
        return getReport(null, null)
    }

    /**
     * Invokes the use case to get a report of cash outflows within a specific date range.
     */
    operator fun invoke(startDate: Long, endDate: Long): Flow<List<DailyCashOutReport>> {
        return getReport(startDate, endDate)
    }

    private fun getReport(startDate: Long?, endDate: Long?): Flow<List<DailyCashOutReport>> {
        return combine(
            cashOutRepository.getAllCashOuts(),
            userRepository.getAllUsers()
        ) { allOutflows, allUsers ->
            val filteredOutflows = if (startDate != null && endDate != null) {
                allOutflows.filter { it.createdAt in startDate..endDate }
            } else {
                allOutflows
            }

            if (filteredOutflows.isEmpty()) {
                return@combine emptyList<DailyCashOutReport>()
            }

            val userMap = allUsers.associateBy { it.id }

            val groupedByDay = filteredOutflows.groupBy {
                val cal = Calendar.getInstance()
                cal.timeInMillis = it.createdAt
                cal.set(Calendar.HOUR_OF_DAY, 0)
                cal.set(Calendar.MINUTE, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
                cal.timeInMillis
            }

            groupedByDay.entries
                .sortedByDescending { it.key }
                .map { (_, dailyOutflows) ->
                    val firstTransaction = dailyOutflows.first()
                    val dailyTotal = dailyOutflows.sumOf { it.amount }

                    val transactions = dailyOutflows.map { outflow ->
                        val user = userMap[outflow.userId]

                        CashOutReportItem(
                            id = outflow.id,
                            time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(outflow.createdAt),
                            paidBy = user?.name ?: "N/A",
                            note = outflow.note.ifEmpty { "-" },
                            amount = outflow.amount.toRupiahFormat(),
                            outcomeType = outflow.outcomeType
                        )
                    }

                    DailyCashOutReport(
                        formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(firstTransaction.createdAt),
                        dailyTotal = dailyTotal.toRupiahFormat(),
                        transactions = transactions
                    )
                }
        }
    }
}
