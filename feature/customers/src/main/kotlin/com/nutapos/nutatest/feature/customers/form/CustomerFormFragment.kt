package com.nutapos.nutatest.feature.customers.form

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
class CustomerFormFragment : Fragment() {

  private val viewModel: CustomerFormViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          CustomerFormScreen(
            onNavigateBack = { findNavController().popBackStack() },
            onSaveCustomer = {
              // TODO: Implement save customer logic
              findNavController().popBackStack()
            }
          )
        }
      }
    }
  }
}
