package com.nutapos.nutatest.feature.cash_out.dialog

sealed class SourceOutcomeType(val title: String, val description: String) {
  object Income : SourceOutcomeType("Pendapatan", "Dana diambil dari total pendapatan di kasir. Mempengaruhi laba", )
  object Modal : SourceOutcomeType("Modal", "Dana diambil dari modal di kasir. Tidak mempengaruhi laba")
}
