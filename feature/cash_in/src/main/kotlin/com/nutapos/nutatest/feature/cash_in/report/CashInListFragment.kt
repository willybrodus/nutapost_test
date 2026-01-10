package com.nutapos.nutatest.feature.cash_in.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.*
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashInListFragment : Fragment() {

  private val viewModel: CashInViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        MaterialTheme {
          CashInListScreen(
            onNavigateBack = { findNavController().popBackStack() },
            onNavigateToCreate = { navigateTo("nutatest://cash_in_create") }
          )
        }
      }
    }
  }

  private fun navigateTo(deeplink: String) {
    val request = NavDeepLinkRequest.Builder
      .fromUri(deeplink.toUri())
      .build()
    findNavController().navigate(request)
  }
}
