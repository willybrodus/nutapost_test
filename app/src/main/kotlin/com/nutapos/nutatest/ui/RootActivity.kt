package com.nutapos.nutatest.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import com.nutapos.nutatest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_root)

    // Force status bar color to GreenMain and icons to light
    window.statusBarColor = Color.parseColor("#3DAE2F")
    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

    val navHostFragment = supportFragmentManager
      .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
  }
}
