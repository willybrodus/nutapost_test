package com.nutapos.nutatest.core.domain.model.cash_out

import com.nutapos.nutatest.core.domain.model.user.User

data class CashOut(
  val id: Long,
  val user: User,
  val note: String,
  val amount: Double,
  val sourceOutcomeType: String,
  val proof: String?,
  val createdAt: Long
)
