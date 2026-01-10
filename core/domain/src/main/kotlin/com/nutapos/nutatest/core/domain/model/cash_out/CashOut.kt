package com.nutapos.nutatest.core.domain.model.cash_out

data class CashOut(
    val id: Long,
    val userId: Long,
    val note: String,
    val amount: Double,
    val outcomeType: String,
    val proof: String?,
    val createdAt: Long
)
