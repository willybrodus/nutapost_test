package com.nutapos.nutatest.feature.cash_out.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.cash_out.CashOut
import com.nutapos.nutatest.core.domain.usecase.cash_out.DeleteCashOutUseCase
import com.nutapos.nutatest.core.domain.usecase.cash_out.GetCashOutDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CashOutDetailViewModel @Inject constructor(
    private val getCashOutDetailUseCase: GetCashOutDetailUseCase,
    private val deleteCashOutUseCase: DeleteCashOutUseCase
) : ViewModel() {

    private val _cashOut = MutableStateFlow<CashOut?>(null)
    val cashOut = _cashOut.asStateFlow()

    private val _showToast = MutableSharedFlow<String>()
    val showToast = _showToast.asSharedFlow()

    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()

    fun loadCashOut(id: Long) {
        viewModelScope.launch {
            getCashOutDetailUseCase(id).collect {
                _cashOut.value = it
            }
        }
    }

    fun deleteCashOut() {
        viewModelScope.launch {
            _cashOut.value?.let {
                try {
                    deleteCashOutUseCase(it)
                    _showToast.emit("berhasil dihapus")
                    _navigateBack.emit(Unit)
                } catch (e: Exception) {
                    _showToast.emit("Gagal menghapus: ${e.message}")
                }
            }
        }
    }
}
