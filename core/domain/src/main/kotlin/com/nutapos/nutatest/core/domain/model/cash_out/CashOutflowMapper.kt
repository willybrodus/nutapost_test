package com.nutapos.nutatest.core.domain.model.cash_out

import com.nutapos.nutatest.core.local.entity.CashOut as EntityCashOut

fun CashOutflowFormState.toEntity(userId: Long): EntityCashOut {
    val cleanAmount = amount.replace(Regex("[^\\d]"), "").toDoubleOrNull() ?: 0.0
    return EntityCashOut(
        userId = userId,
        note = description,
        amount = cleanAmount,
        outcomeType = outcomeType,
        proof = proofImageUrl,
        createdAt = System.currentTimeMillis()
    )
}

fun EntityCashOut.toDomainModel():CashOut {
    return CashOut(
        id = this.id,
        userId = this.userId,
        note = this.note,
        amount = this.amount,
        outcomeType = this.outcomeType,
        proof = this.proof,
        createdAt = this.createdAt
    )
}
