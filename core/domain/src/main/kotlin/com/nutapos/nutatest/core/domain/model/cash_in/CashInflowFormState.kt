package com.nutapos.nutatest.core.domain.model.cash_in

import com.nutapos.nutatest.core.local.entity.Customer

data class CashInflowFormState(
  val createdDateTime: String,
  val receivedIn: String, // e.g., "Kasir Perangkat ke-49"
  val customer: Customer, // e.g., "Mira Workman"
  val description: String,
  val amount: String, // e.g., "Rp 150.000"
  val incomeType: String,
  val proofImageUrl: String?
)