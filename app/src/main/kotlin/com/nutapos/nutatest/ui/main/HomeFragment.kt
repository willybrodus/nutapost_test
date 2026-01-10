package com.nutapos.nutatest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.databinding.FragmentHomeBinding
import com.nutapos.nutatest.feature.home.HomeScreen
import com.nutapos.nutatest.feature.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  private val homeViewModel: HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    binding.composeView.apply {
      setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
      setContent {
        NutaTestTheme {
          HomeScreen(
            homeViewModel = homeViewModel,
            onNavigateToCashIn = { navigateTo(com.nutapos.nutatest.feature.cash_in.R.id.nav_cash_in) },
            onNavigateToCashOut = { navigateTo(com.nutapos.nutatest.feature.cash_out.R.id.nav_cash_out) },
            onExitApp = {
              activity?.finish()
            }
          )
        }
      }
    }
    return view
  }

  private fun navigateTo(destinationId: Int, navOptions: NavOptions? = null) {
    findNavController().navigate(destinationId, null, navOptions)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
