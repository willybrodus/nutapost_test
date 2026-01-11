package com.nutapos.nutatest.feature.customers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.customers.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
          CustomerListScreen(
            viewModel = viewModel,
            onNavigateBack = { findNavController().popBackStack() },
            onNavigateToCreate = {
              findNavController().navigate(R.id.action_customerListFragment_to_customerFormFragment)
            }
          )
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    findNavController().currentBackStackEntry?.savedStateHandle?.let {
      it.getStateFlow<Long>("new_customer_id", -1L)
        .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .onEach { customerId ->
          if (customerId != -1L) {
            viewModel.onNewCustomerCreated(customerId)
            it.remove<Long>("new_customer_id")
          }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
  }
}
