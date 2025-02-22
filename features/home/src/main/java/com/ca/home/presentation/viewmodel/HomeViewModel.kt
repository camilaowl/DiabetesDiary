package com.ca.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.usecase.GetRecordsByDateUseCase
import com.ca.domain.usecase.GetRemindersUseCase
import com.ca.domain.usecase.MarkInsulinReminderAsDoneUseCase
import com.ca.model.RecordInsulinReminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemindersUseCase: GetRemindersUseCase,
    private val getRecordsUseCase: GetRecordsByDateUseCase,
    private val markInsulinReminderAsDoneUseCase: MarkInsulinReminderAsDoneUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
        get() = _viewState
            .onStart {
                fetchRemindersByDate(viewState.value.selectedDate)
                fetchRecordsByDate(viewState.value.selectedDate)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                HomeViewState()
            )

    private fun fetchRemindersByDate(date: LocalDate) {
        viewModelScope.launch {
            getRemindersUseCase().collect { reminders ->
                _viewState.update {
                    it.copy(reminders = reminders)
                }
            }
        }
    }

    private fun fetchRecordsByDate(date: LocalDate) {
        viewModelScope.launch {
            getRecordsUseCase(date).collect { recordsByDate ->
                _viewState.update {
                    it.copy(recordsByDate = recordsByDate)
                }
            }
        }
    }

    fun selectDate(date: LocalDate) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(selectedDate = date)
            }
            fetchRemindersByDate(date)
            fetchRecordsByDate(date)
        }
    }

    fun markInsulinReminderAsDone(reminder: RecordInsulinReminder) {
        viewModelScope.launch {
            markInsulinReminderAsDoneUseCase(reminder)
        }
    }
}

