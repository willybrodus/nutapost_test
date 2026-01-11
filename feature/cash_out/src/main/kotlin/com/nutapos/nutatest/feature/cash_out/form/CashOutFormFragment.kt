package com.nutapos.nutatest.feature.cash_out.form

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
import com.nutapos.nutatest.core.domain.model.cash_out.CashOutflowFormState
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashOutFormFragment : Fragment() {

    private val viewModel: CashOutFormViewModel by viewModels()
    private val args: CashOutFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val cashOut by viewModel.cashOut.collectAsState()
                val loggedInUser by viewModel.loggedInUser.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()

                LaunchedEffect(args.cashOutId) {
                    if (args.cashOutId != -1L) {
                        viewModel.loadCashOut(args.cashOutId)
                    }
                }

                NutaTestTheme {
                    CashOutFormScreen(
                        cashOut = cashOut,
                        loggedInUserName = loggedInUser?.name ?: "",
                        isLoading = isLoading,
                        onBackClick = { findNavController().popBackStack() },
                        onSaveCashOut = { formState ->
                            if (args.cashOutId != -1L) {
                                viewModel.updateCashOut(formState)
                            } else {
                                viewModel.createCashOut(formState)
                            }
                        },

                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.showToast.collectLatest { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                if (!viewModel.isLoading.value) {
                    findNavController().popBackStack()
                }
            }
        }
    }
}
