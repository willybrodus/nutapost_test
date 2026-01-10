package com.nutapos.nutatest.feature.customers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerListFragment : Fragment() {

  private val viewModel: CustomerListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          // TODO: Replace with actual data from ViewModel
          CustomerListScreen(
            onNavigateBack = { findNavController().popBackStack() },
            onNavigateToCreate = {
              // TODO: Navigate to create customer screen
            },
            onCustomerSelected = {
              // TODO: Handle customer selection
              findNavController().popBackStack()
            },
            customers = emptyList(),
            selectedCustomer = null
          )
        }
      }
    }
  }
}
