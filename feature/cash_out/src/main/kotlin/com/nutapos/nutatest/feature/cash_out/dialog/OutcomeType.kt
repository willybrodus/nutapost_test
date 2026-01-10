package com.nutapos.nutatest.feature.cash_out.dialog

sealed class OutcomeType(val title: String, val description: String) {
  object Income : OutcomeType("Pengeluaran (diambil dari pendapatan)", "Mempengaruhi laba")
  object Modal : OutcomeType("Pengeluaran (diambil dari modal)", "Tidak mempengaruhi laba")
}
