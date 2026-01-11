package com.nutapos.nutatest.feature.cash_out.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nutapos.nutatest.core.ui.theme.NutaTestTheme
import com.nutapos.nutatest.feature.cash_out.list.components.CashOutTransaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashOutListFragment : Fragment() {

    private val viewModel: CashOutListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NutaTestTheme {
                    val cashOutReport by viewModel.cashOutReport.collectAsState()
                    val dateRange by viewModel.dateRange.collectAsState()

                    CashOutListScreen(
                        onNavigateBack = { findNavController().popBackStack() },
                        onNavigateToCreate = {
                            val action = CashOutListFragmentDirections.actionCashOutListFragmentToCashOutFormFragment()
                            findNavController().navigate(action)
                        },
                        groups = cashOutReport.map {
                            CashOutGroup(
                                date = it.formattedDate,
                                total = it.dailyTotal,
                                transactions = it.transactions.map { item ->
                                  CashOutTransaction(
                                    time = item.time,
                                    cashOutFrom = item.paidBy,
                                    amount = item.amount,
                                    description = item.note,
                                    outcomeType = item.outcomeType
                                  )
                                }
                            )
                        },
                        dateRange = dateRange,
                        onDateRangeSelected = { startDate, endDate ->
                            viewModel.setDateRange(startDate, endDate)
                        }
                    )
                }
            }
        }
    }
}
