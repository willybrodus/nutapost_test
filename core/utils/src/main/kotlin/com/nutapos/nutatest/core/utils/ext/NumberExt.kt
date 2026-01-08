package com.nutapos.nutatest.core.utils.ext

import java.text.NumberFormat
import java.util.Locale

/**
 * Formats a number into a Rupiah currency string.
 * e.g., 150000.0 -> "Rp 150.000"
 */
fun Number.toRupiahFormat(): String {
    val formatter = NumberFormat.getNumberInstance(Locale("id", "ID"))
    return "Rp ${formatter.format(this)}"
}
