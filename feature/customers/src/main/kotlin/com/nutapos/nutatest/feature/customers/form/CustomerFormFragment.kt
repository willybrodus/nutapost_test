package com.nutapos.nutatest.feature.customers.form

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
class CustomerFormFragment : Fragment() {

    private val viewModel: CustomerFormViewModel by viewModels()
    private val args: CustomerFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val customer by viewModel.customer.collectAsState()
                val isLoading by viewModel.isLoading.collectAsState()

                LaunchedEffect(args.customerId) {
                    if (args.customerId != -1L) {
                        viewModel.loadCustomer(args.customerId)
                    }
                }

                NutaTestTheme {
                    CustomerFormScreen(
                        customer = customer,
                        isLoading = isLoading,
                        onNavigateBack = { findNavController().popBackStack() },
                        onSaveCustomer = { name, phoneNumber, email, isMember ->
                            if (args.customerId != -1L) {
                                viewModel.updateCustomer(args.customerId, name, phoneNumber, email, isMember)
                            } else {
                                viewModel.createCustomer(name, phoneNumber, email, isMember)
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.createdCustomerId.collectLatest { customerId ->
                if (customerId != null) {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("new_customer_id", customerId)
                    findNavController().popBackStack()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.showToast.collectLatest { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
