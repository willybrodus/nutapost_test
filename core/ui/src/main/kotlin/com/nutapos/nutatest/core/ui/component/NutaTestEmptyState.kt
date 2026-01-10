package com.nutapos.nutatest.core.ui.component

import androidx.annotation.DrawableRes
import androidx.appcompat.R as appCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.R
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme

@Composable
fun NutaTestEmptyState(
  title: String,
  description: String,
  modifier: Modifier = Modifier,
  @DrawableRes image: Int? = R.drawable.empty_state_icon,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max)
          .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      image?.let {
        Image(
          painter = painterResource(id = image),
          contentDescription = title,
          modifier = Modifier.size(200.dp) // Ukuran bisa disesuaikan
        )
      }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutaTestEmptyStatePreview() {
    NutaTestTheme {
        Surface {
            NutaTestEmptyState(
                // Pastikan Anda memiliki resource drawable dengan nama ini
                title = "Belum Ada Transaksi Uang Masuk",
                description = "Silahkan atur periode untuk menampilkan transaksi uang masuk."
            )
        }
    }
}
