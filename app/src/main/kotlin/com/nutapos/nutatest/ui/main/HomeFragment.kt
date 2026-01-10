package com.nutapos.nutatest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
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
                    HomeScreen(
                        homeViewModel = homeViewModel,
                        onNavigateToCashIn = { navigateTo("nutatest://cash_in") },
                        onNavigateToCashOut = { navigateTo("nutatest://cash_out") }
                    )
                }
            }
        }
    }

    private fun navigateTo(deeplink: String, navOptions: NavOptions? = null) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(deeplink.toUri())
            .build()
        findNavController().navigate(request, navOptions)
    }
}
