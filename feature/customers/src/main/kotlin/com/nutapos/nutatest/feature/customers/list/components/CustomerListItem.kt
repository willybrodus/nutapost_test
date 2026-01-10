package com.nutapos.nutatest.feature.customers.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.component.MemberLabel
import com.nutapos.nutatest.core.ui.theme.GreenSecondary
import com.nutapos.nutatest.core.ui.theme.Text4
import com.nutapos.nutatest.feature.customers.R

@Composable
fun CustomerListItem(
  name: String,
  phoneNumber: String?,
  email: String?,
  isMember: Boolean,
  isSelected: Boolean
) {
  Row(
    modifier = Modifier.padding(vertical = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .background(GreenSecondary),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = name.first().toString(),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500),
        color = com.nutapos.nutatest.core.ui.theme.GreenMain
      )
    }

    Spacer(modifier = Modifier.width(16.dp))

    Column(modifier = Modifier.weight(1f)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = name,
          style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
        )
        if (isMember) {
          Spacer(modifier = Modifier.width(8.dp))
          MemberLabel("Member")
        }
      }

      phoneNumber?.let {
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            painter = painterResource(id = R.drawable.ic_phone),
            contentDescription = null,
            tint = Text4,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = it, style = MaterialTheme.typography.bodyMedium, color = Text4)
        }
      }

      email?.let {
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            painter = painterResource(id = R.drawable.ic_email),
            contentDescription = null,
            tint = Text4,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = it, style = MaterialTheme.typography.bodyMedium, color = Text4)
        }
      }
    }

    if (isSelected) {
      Icon(
        painter = painterResource(id = R.drawable.ic_check_mark),
        contentDescription = "Selected",
        tint = com.nutapos.nutatest.core.ui.theme.GreenMain
      )
    }
  }
}
