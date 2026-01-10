package com.nutapos.nutatest.core.domain.model.cash_in

import com.nutapos.nutatest.core.local.entity.CashInflow
import java.text.NumberFormat
import java.util.Locale

fun CashInflowFormState.toEntity(userId: Long): CashInflow {
    // Remove non-numeric characters and parse the amount
    val cleanAmount = amount.replace(Regex("[^\\d]"), "").toDoubleOrNull() ?: 0.0

    return CashInflow(
        userId = userId,
        customerId = customer.id,
        description = description,
        amount = cleanAmount,
        incomeType = incomeType,
        proof = proofImageUrl,
        createdAt = System.currentTimeMillis() // Assuming we always set a new timestamp on create/update
    )
}
