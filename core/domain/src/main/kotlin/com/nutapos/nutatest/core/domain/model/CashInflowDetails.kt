package com.nutapos.nutatest.core.domain.model

data class CashInflowDetails(
    val createdDateTime: String,
    val receivedIn: String, // e.g., "Kasir Perangkat ke-49"
    val receivedFrom: String, // e.g., "Mira Workman"
    val description: String,
    val amount: String, // e.g., "Rp 150.000"
    val incomeType: String,
    val proofImageUrl: String?
)
