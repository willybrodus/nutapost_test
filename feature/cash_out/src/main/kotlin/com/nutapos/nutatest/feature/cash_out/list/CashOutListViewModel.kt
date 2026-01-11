package com.nutapos.nutatest.feature.cash_out.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutapos.nutatest.core.domain.model.cash_out.DailyCashOutReport
import com.nutapos.nutatest.core.domain.usecase.cash_out.GetCashOutReportUseCase
import com.nutapos.nutatest.core.domain.usecase.session.GetLoggedInUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CashOutListViewModel @Inject constructor(
    private val getCashOutReportUseCase: GetCashOutReportUseCase,
    private val getLoggedInUserIdUseCase: GetLoggedInUserIdUseCase
) : ViewModel() {

    private val _cashOutReport = MutableStateFlow<List<DailyCashOutReport>>(emptyList())
    val cashOutReport: StateFlow<List<DailyCashOutReport>> = _cashOutReport

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _dateRange = MutableStateFlow<Pair<Date?, Date?>>(Pair(null, null))
    val dateRange: StateFlow<Pair<Date?, Date?>> = _dateRange

    init {
        viewModelScope.launch {
            getLoggedInUserIdUseCase().collectLatest {
                it?.let { userId ->
                    _isLoading.value = true
                    getCashOutReportUseCase(userId).collectLatest { report ->
                        _cashOutReport.value = report
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun setDateRange(startDate: Date, endDate: Date) {
        _dateRange.value = Pair(startDate, endDate)
        viewModelScope.launch {
            getLoggedInUserIdUseCase().collectLatest {
                it?.let { userId ->
                    _isLoading.value = true
                    getCashOutReportUseCase(userId, startDate.time, endDate.time).collectLatest { report ->
                        _cashOutReport.value = report
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}
