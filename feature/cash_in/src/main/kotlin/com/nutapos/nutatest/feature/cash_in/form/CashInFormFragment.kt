package com.nutapos.nutatest.feature.cash_in.form

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
class CashInFormFragment : Fragment() {

    private val viewModel: CashInFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NutaTestTheme {
                    CashInFormScreen(
                        onBackClick = { findNavController().popBackStack() },
                        onNavigateToCustomerSelection = { 
                            // TODO: Implement customer selection navigation
                        }
                    )
                }
            }
        }
    }
}
