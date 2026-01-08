package com.nutapos.nutatest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nutapos.nutatest.databinding.ActivityRootBinding
import com.nutapos.nutatest.ui.dialog.FinanceDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

  private lateinit var binding: ActivityRootBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityRootBinding.inflate(layoutInflater)
    setContentView(binding.root)
    showFinanceDialog()
  }

  private fun showFinanceDialog() {
    val dialog = FinanceDialog(
      onCashInClick = {
        Toast.makeText(this, "Uang Masuk Ditekan", Toast.LENGTH_SHORT).show()
        // Navigasi ke halaman Uang Masuk bisa ditambahkan di sini
      },
      onCashOutClick = {
        Toast.makeText(this, "Uang Keluar Ditekan", Toast.LENGTH_SHORT).show()
        // Navigasi ke halaman Uang Keluar bisa ditambahkan di sini
      }
    )
    dialog.show(supportFragmentManager, FinanceDialog.TAG)
  }
}
