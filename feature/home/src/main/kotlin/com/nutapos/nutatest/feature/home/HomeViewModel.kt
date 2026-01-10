package com.nutapos.nutatest.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.domain.usecase.session.GetLoggedInUserIdUseCase
import com.nutapos.nutatest.core.domain.usecase.session.SetLoggedInUserIdUseCase
import com.nutapos.nutatest.core.domain.usecase.user.GetAllUsersUseCase
import com.nutapos.nutatest.core.domain.usecase.user.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
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
    private val getLoggedInUserIdUseCase: GetLoggedInUserIdUseCase,
    private val setLoggedInUserIdUseCase: SetLoggedInUserIdUseCase,
    private val insertUserUseCase: InsertUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            combine(
                getAllUsersUseCase(),
                getLoggedInUserIdUseCase()
            ) { users, loggedInUserId ->
                _uiState.update {
                    it.copy(
                        users = users,
                        loggedInUser = users.find { user -> user.id == loggedInUserId },
                        isLoading = false
                    )
                }
            }.collect()
        }
    }

    fun createUser(name: String) {
        viewModelScope.launch {
            val newUser = User(name = name)
            val newUserId = insertUserUseCase(newUser)
            setLoggedInUserIdUseCase(newUserId)
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
            setLoggedInUserIdUseCase(user.id)
        }
    }

    fun logout() {
        viewModelScope.launch {
            setLoggedInUserIdUseCase(null)
        }
    }
}
