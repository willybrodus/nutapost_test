package com.nutapos.nutatest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.nutapos.nutatest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private val viewModel: HomeViewModel by viewModels()

  private val navHomeOptions: NavOptions
    get() = navOptions {
      popUpTo(
        R.id.home_navigation_graph,
        popUpToBuilder = { inclusive = true })
    }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        MaterialTheme {
          HomeScreen(
            onClose = { requireActivity().finish() },
            onNavigateTo = { navigateTo(it) }
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

@Composable
private fun HomeScreen(
  onClose: () -> Unit,
  onNavigateTo: (String) -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .background(Color(0xFFAAAAAA))
      )
    }

    // Bottom UI
    Card(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth(),
      shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 24.dp)
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
          Text(
            text = stringResource(id = R.string.finance),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
          )
          IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.CenterEnd)
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color(0xFF424242)
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MenuItem(
          title = stringResource(id = R.string.cash_in),
          onClick = {
            onNavigateTo("nutatest://cash_in")
          }
        )
        MenuItem(
          title = stringResource(id = R.string.cash_out),
          onClick = {
            onNavigateTo("nutatest://cash_out")
          }
        )
      }
    }
  }
}

@Composable
private fun MenuItem(title: String, onClick: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(horizontal = 24.dp, vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = title,
      modifier = Modifier.weight(1f),
      fontSize = 16.sp,
      color = Color(0xFF424242)
    )
    Icon(
      imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
      contentDescription = null,
      tint = Color(0xFF424242)
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
  MaterialTheme {
    HomeScreen(onClose = {}, onNavigateTo = {})
  }
}
