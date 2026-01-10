package com.nutapos.nutatest.feature.cash_out.detail

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
class CashOutDetailFragment : Fragment() {

  private val viewModel: CashOutDetailViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          CashOutDetailScreen(
            onBackClick = {
              //findNavController().popBackStack()
            },
            onEditClick = {
              //findNavController().navigate(R.id.action_cashOutDetailFragment_to_cashOutFormFragment)
            },
            onDeleteClick = {
              // TODO: Show confirmation dialog before deleting
              //findNavController().popBackStack()
            },
            proofImageUri = null // TODO: Pass the actual URI from ViewModel
          )
        }
      }
    }
  }
}
