package com.nutapos.nutatest.feature.proof

import android.net.Uri
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
class ProofViewerFragment : Fragment() {

    private val viewModel: ProofViewerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NutaTestTheme {
                    // TODO: Get the actual image URI from arguments or ViewModel
                    ProofViewerScreen(
                        imageUri = Uri.EMPTY,
                        onDismiss = { findNavController().popBackStack() }
                    )
                }
            }
        }
    }
}
