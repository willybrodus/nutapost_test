package com.nutapos.nutatest.feature.cash_out.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashOutDetailFragment : Fragment() {

    private val viewModel: CashOutDetailViewModel by viewModels()
    private val args: CashOutDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NutaTestTheme {
                    val cashOut by viewModel.cashOut.collectAsState()

                    LaunchedEffect(args.cashOutId) {
                        viewModel.loadCashOut(args.cashOutId)
                    }

                    cashOut?.let {
                        CashOutDetailScreen(
                            cashOut = it,
                            onBackClick = { findNavController().popBackStack() },
                            onEditClick = {
                                val action = CashOutDetailFragmentDirections.actionCashOutDetailFragmentToCashOutFormFragment(it.id)
                                findNavController().navigate(action)
                            },
                            onDeleteClick = { viewModel.deleteCashOut() }
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.showToast.collectLatest { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateBack.collectLatest {
                findNavController().popBackStack()
            }
        }
    }
}
