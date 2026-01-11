package com.nutapos.nutatest.feature.cash_out.dialog

sealed class OutcomeType(val title: String, val description: String) {
  object Income : OutcomeType("Pendapatan", "Dana diambil dari total pendapatan di kasir. Mempengaruhi laba", )
  object Modal : OutcomeType("Modal", "Dana diambil dari modal di kasir. Tidak mempengaruhi laba")
}
