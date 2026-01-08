package com.nutapos.nutatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nutapos.nutatest.databinding.ActivitySplashBinding
import com.nutapos.nutatest.ui.dialog.SampleDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

  private lateinit var binding: ActivitySplashBinding

  private val sampleDialog: SampleDialog by lazy {
    SampleDialog(binding.container)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySplashBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.btnUpdate.setOnClickListener {
      showDialog()
    }
  }

  private fun showDialog() {
    sampleDialog.setDescription("Only testing the UI, hope everythings is ok")
    sampleDialog.show()
  }
}
