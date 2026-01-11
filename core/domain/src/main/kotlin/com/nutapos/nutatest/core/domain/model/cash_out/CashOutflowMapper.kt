package com.nutapos.nutatest.core.domain.model.cash_out

import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.local.entity.CashOut as EntityCashOut

fun CashOutflowFormState.toEntity(userId: Long): EntityCashOut {
    val cleanAmount = amount.replace(Regex("[^\\d]"), "").toDoubleOrNull() ?: 0.0
    return EntityCashOut(
        userId = userId,
        note = description,
        amount = cleanAmount,
        sourceOutcomeType = sourceOutcomeType,
        proof = proofImageUrl,
        createdAt = System.currentTimeMillis()
    )
}

fun EntityCashOut.toDomainModel(user: User): CashOut {
    return CashOut(
        id = this.id,
        user = user,
        note = this.note,
        amount = this.amount,
        sourceOutcomeType = sourceOutcomeType,
        proof = this.proof,
        createdAt = this.createdAt
    )
}

fun CashOut.toEntity(): EntityCashOut{
  return EntityCashOut(
    id = this.id,
    userId = this.user.id,
    note = this.note,
    amount = this.amount,
    sourceOutcomeType = this.sourceOutcomeType,
    proof = this.proof,
    createdAt = this.createdAt
  )
}
