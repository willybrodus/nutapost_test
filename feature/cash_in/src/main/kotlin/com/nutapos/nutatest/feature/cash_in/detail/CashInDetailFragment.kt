package com.nutapos.nutatest.feature.cash_in.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.cash_in.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashInDetailFragment : Fragment() {

  private val viewModel: CashInDetailViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        NutaTestTheme {
          CashInDetailScreen(
            onBackClick = { findNavController().popBackStack() },
            onEditClick = {
              //findNavController().navigate(R.id.action_cashInDetailFragment_to_cashInFormFragment)
            },
            onDeleteClick = {
              // TODO: Show confirmation dialog before deleting
              findNavController().popBackStack()
            },
            null
          )
        }
      }
    }
  }
}
