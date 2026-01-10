package com.nutapos.nutatest.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.domain.usecase.user.GetAllUsersUseCase
import com.nutapos.nutatest.core.domain.usecase.user.InsertUserUseCase
import com.nutapos.nutatest.core.domain.usecase.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
  val users: List<User> = emptyList(),
  val loggedInUser: User? = null,
  val isLoading: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            getAllUsersUseCase().collect { users ->
                _uiState.update {
                    it.copy(
                        users = users,
                        loggedInUser = users.find { user -> user.isLogin },
                        isLoading = false
                    )
                }
            }
        }
    }

    fun createUser(name: String) {
        viewModelScope.launch {
            val newUser = User(name = name, isLogin = true)
            // Log out other users first
            _uiState.value.loggedInUser?.let { logout(it) }
            insertUserUseCase(newUser)
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
            // Log out other users first
            _uiState.value.loggedInUser?.let { logout(it) }
            updateUserUseCase(user.copy(isLogin = true))
        }
    }

    fun logout(user: User) {
        viewModelScope.launch {
            updateUserUseCase(user.copy(isLogin = false))
        }
    }
}
