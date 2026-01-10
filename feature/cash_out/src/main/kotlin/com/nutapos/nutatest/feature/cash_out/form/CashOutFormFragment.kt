package com.nutapos.nutatest.feature.cash_out.form

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
class CashOutFormFragment : Fragment() {

  private val viewModel: CashOutFormViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          CashOutFormScreen(
            onBackClick = {
              //findNavController().popBackStack()
            },
            onNavigateToPaidToSelection = {
              // TODO: Implement contact selection navigation
            }
          )
        }
      }
    }
  }
}
