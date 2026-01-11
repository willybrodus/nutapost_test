package com.nutapos.nutatest.feature.cash_out.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.model.cash_out.CashOutflowFormState
import com.nutapos.nutatest.core.domain.model.user.User
import com.nutapos.nutatest.core.domain.usecase.cash_out.GetCashOutByIdUseCase
import com.nutapos.nutatest.core.domain.usecase.cash_out.InsertCashOutUseCase
import com.nutapos.nutatest.core.domain.usecase.cash_out.UpdateCashOutUseCase
import com.nutapos.nutatest.core.domain.usecase.session.GetLoggedInUserIdUseCase
import com.nutapos.nutatest.core.domain.usecase.user.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashOutFormViewModel @Inject constructor(
  private val getCashOutByIdUseCase: GetCashOutByIdUseCase,
  private val insertCashOutUseCase: InsertCashOutUseCase,
  private val updateCashOutUseCase: UpdateCashOutUseCase,
  private val getLoggedInUserUseCase: GetLoggedInUserIdUseCase,
  private val getUserLogin: GetUserByIdUseCase
) : ViewModel() {

  private val _cashOut = MutableStateFlow<CashOut?>(null)
  val cashOut = _cashOut.asStateFlow()

  private val _loggedInUser = MutableStateFlow<User?>(null)
  val loggedInUser = _loggedInUser.asStateFlow()

  private val _isLoading = MutableStateFlow(false)
  val isLoading = _isLoading.asStateFlow()

  private val _showToast = MutableSharedFlow<String>()
  val showToast = _showToast.asSharedFlow()

  private val _navigateBack = MutableSharedFlow<Unit>()
  val navigateBack = _navigateBack.asSharedFlow()

  init {
    viewModelScope.launch {
      _loggedInUser.value = getLoggedInUserUseCase().first()?.let {
        getUserLogin(it)
      }?.first()
    }
  }

  fun loadCashOut(cashOutId: Long) {
    viewModelScope.launch {
      val user = _loggedInUser.value
      if (user != null) {
        getCashOutByIdUseCase(cashOutId, user).collect {
          _cashOut.value = it
        }
      } else {
        _showToast.emit("Gagal: Pengguna tidak ditemukan")
      }
    }
  }

  fun createCashOut(formState: CashOutflowFormState) {
    viewModelScope.launch {
      _isLoading.value = true
      try {
        val user = _loggedInUser.value
        if (user != null) {
          insertCashOutUseCase(formState, user.id)
          _showToast.emit("berhasil disimpan")
          _navigateBack.emit(Unit)
        } else {
          _showToast.emit("Gagal: Pengguna tidak ditemukan")
        }
      } catch (e: Exception) {
        _showToast.emit("Gagal menyimpan: ${e.message}")
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun updateCashOut(formState: CashOutflowFormState) {
    viewModelScope.launch {
      _isLoading.value = true
      try {
        val user = _loggedInUser.value
        if (user != null) {
          updateCashOutUseCase(formState, user.id)
          _showToast.emit("berhasil diupdate")
          _navigateBack.emit(Unit)
        } else {
          _showToast.emit("Gagal: Pengguna tidak ditemukan")
        }
      } catch (e: Exception) {
        _showToast.emit("Gagal mengupdate: ${e.message}")
      } finally {
        _isLoading.value = false
      }
    }
  }
}
