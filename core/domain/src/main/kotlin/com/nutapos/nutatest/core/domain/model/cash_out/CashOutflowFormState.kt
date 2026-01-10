package com.nutapos.nutatest.core.domain.model.cash_out

data class CashOutflowFormState(
    val paidFrom: String,
    val description: String,
    val amount: String,
    val outcomeType: String,
    val proofImageUrl: String?
)
