package com.nutapos.nutatest.core.domain.model.cash_out

data class DailyCashOutReport(
    val formattedDate: String,
    val dailyTotal: String,
    val transactions: List<CashOutReportItem>
)

data class CashOutReportItem(
    val id: Long,
    val time: String,
    val paidBy: String,
    val note: String,
    val amount: String,
    val sourceOutcomeType: String
)
