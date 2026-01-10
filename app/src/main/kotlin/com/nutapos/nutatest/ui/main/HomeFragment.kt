package com.nutapos.nutatest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.home.HomeScreen
import com.nutapos.nutatest.feature.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NutaTestTheme {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        HomeScreen(
                            homeViewModel = homeViewModel,
                            onNavigateToCashIn = { findNavController().navigate(com.nutapos.nutatest.feature.cash_in.R.id.nav_cash_in) },
                            onNavigateToCashOut = { findNavController().navigate(com.nutapos.nutatest.feature.cash_out.R.id.nav_cash_out) },
                            onExitApp = { activity?.finish() }
                        )
                    }
                }
            }
        }
    }
}
