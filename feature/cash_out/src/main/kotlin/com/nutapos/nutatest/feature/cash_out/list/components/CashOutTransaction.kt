package com.nutapos.nutatest.feature.cash_out.list.components

data class CashOutTransaction(
    val time: String,
    val cashOutFrom: String,
    val amount: String,
    val description: String?,
    val sourceOutcomeType: String
)
