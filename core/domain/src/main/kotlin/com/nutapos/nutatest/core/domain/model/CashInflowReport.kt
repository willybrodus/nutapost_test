package com.nutapos.nutatest.core.domain.model

/**
 * Represents a single transaction item in the cash inflow report list.
 */
data class CashInflowReportItem(
    val id: Long,
    val time: String, // e.g., "08:42:56"
    val customerName: String,
    val receivedBy: String, // e.g., "Kasir perangkat ke-49"
    val description: String,
    val amount: String // e.g., "Rp 260.000"
)

/**
 * Represents a group of transactions for a single day, including the daily total.
 */
data class DailyCashInflowReport(
    val formattedDate: String, // e.g., "Senin, 22 April 2024"
    val dailyTotal: String, // e.g., "Rp 310.000"
    val transactions: List<CashInflowReportItem>
)
