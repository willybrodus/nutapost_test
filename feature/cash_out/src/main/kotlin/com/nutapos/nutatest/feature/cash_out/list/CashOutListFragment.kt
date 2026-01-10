package com.nutapos.nutatest.feature.cash_out.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashOutListFragment : Fragment() {

  private val viewModel: CashOutListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          CashOutListScreen(
            onNavigateBack = {
              //findNavController().popBackStack()
            },
            onNavigateToCreate = {
              //findNavController().navigate(R.id.action_cashOutListFragment_to_cashOutFormFragment)
            },
          )
        }
      }
    }
  }
}
