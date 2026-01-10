package com.nutapos.nutatest.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nutapos.nutatest.core.ui.component.NutaTestCtaButton
import com.nutapos.nutatest.feature.user.CreateUserDialog
import com.nutapos.nutatest.feature.user.UserSelectionSheet

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToCashIn: () -> Unit,
    onNavigateToCashOut: () -> Unit
) {
    val uiState by homeViewModel.uiState.collectAsState()
    var showCreateUserDialog by remember { mutableStateOf(false) }
    var showUserSelectionSheet by remember { mutableStateOf(false) }

    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }

        uiState.loggedInUser == null -> {
            // Logged Out State
            LoggedOutContent(
                onCreateUser = { showCreateUserDialog = true },
                onSelectUser = { showUserSelectionSheet = true }
            )
        }

        else -> {
            // Logged In State
            LoggedInContent(
                userName = uiState.loggedInUser!!.name,
                onNavigateToCashIn = onNavigateToCashIn,
                onNavigateToCashOut = onNavigateToCashOut,
                onSwitchUser = { 
                    homeViewModel.logout(uiState.loggedInUser!!)
                }
            )
        }
    }

    if (showCreateUserDialog) {
        CreateUserDialog(
            onDismissRequest = { showCreateUserDialog = false },
            onSaveUser = { name ->
                homeViewModel.createUser(name)
                showCreateUserDialog = false
            }
        )
    }

    if (showUserSelectionSheet) {
        UserSelectionSheet(
            users = uiState.users,
            selectedUser = uiState.loggedInUser,
            onUserSelected = { user ->
                homeViewModel.login(user)
                showUserSelectionSheet = false
            },
            onDismiss = { showUserSelectionSheet = false }
        )
    }
}

@Composable
private fun LoggedInContent(
    userName: String,
    onNavigateToCashIn: () -> Unit,
    onNavigateToCashOut: () -> Unit,
    onSwitchUser: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title_finance, userName),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        HomeMenuItem(text = stringResource(R.string.cash_in_title), onClick = onNavigateToCashIn)
        HorizontalDivider()
        HomeMenuItem(text = stringResource(R.string.cash_out_title), onClick = onNavigateToCashOut)
        HorizontalDivider()
        HomeMenuItem(text = stringResource(R.string.action_switch_user), onClick = onSwitchUser)
    }
}

@Composable
private fun LoggedOutContent(
    onCreateUser: () -> Unit,
    onSelectUser: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NutaTestCtaButton(
            text = stringResource(R.string.action_select_user),
            onClick = onSelectUser,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        NutaTestCtaButton(
            text = stringResource(R.string.action_create_user),
            onClick = onCreateUser,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun HomeMenuItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text, 
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = null)
    }
}

// Previews will be broken because they now require a ViewModel.
// This is expected and can be fixed with a mock ViewModel if needed.
