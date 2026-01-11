package com.nutapos.nutatest.feature.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.ui.component.NutaTestBottomSheet
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSelectionSheet(
  users: List<User> = emptyList(),
  selectedUser: User?,
  onUserSelected: (User) -> Unit,
  onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = null,
        tonalElevation = 0.dp
    ) {
        NutaTestBottomSheet(
            title = stringResource(id = R.string.title_select_user),
            onDismissRequest = onDismiss
        ) {
            LazyColumn {
                items(users) { user ->
                    UserSelectionItem(
                        user = user,
                        isSelected = user.id == selectedUser?.id,
                        onSelect = { onUserSelected(user) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun UserSelectionItem(
    user: User,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelect)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = user.name,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSelectionSheetPreview() {
    val dummyUsers = listOf(
        User(id = 1, name = "John Doe", isLogin = true),
        User(id = 2, name = "Jane Doe", isLogin = false)
    )
    NutaTestTheme {
        UserSelectionSheet(
            users = dummyUsers,
            selectedUser = dummyUsers.first(),
            onUserSelected = {},
            onDismiss = {}
        )
    }
}
