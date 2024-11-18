package com.ca.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.GetRecordsByDateUseCase
import com.ca.domain.usecase.GetRemindersUseCase
import com.ca.domain.usecase.MarkInsulinReminderAsDoneUseCase
import com.ca.model.RecordInsulinReminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
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
                onEvent(HomeEvent.FetchReminders(viewState.value.selectedDate))
                onEvent(HomeEvent.FetchRecordsByDate(viewState.value.selectedDate))
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                HomeViewState()
            )

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.FetchReminders -> {
                viewModelScope.launch {
                    getRemindersUseCase().collect { reminders ->
                        _viewState.update {
                            it.copy(reminders = reminders)
                        }
                    }
                }
            }

            is HomeEvent.FetchRecordsByDate -> {
                viewModelScope.launch {
                    getRecordsUseCase(event.date).collect { recordsByDate ->
                        _viewState.update {
                            it.copy(recordsByDate = recordsByDate)
                        }
                    }
                }
            }

            is HomeEvent.SelectDate -> {
                viewModelScope.launch {
                    _viewState.update {
                        it.copy(selectedDate = event.date)
                    }
                    onEvent(HomeEvent.FetchRecordsByDate(event.date))
                }
            }

            is HomeEvent.AddGlucoseRecord -> {}
            is HomeEvent.AddInsulinRecord -> {}

            else -> {}
        }
    }

    fun markInsulinReminderAsDone(reminder: RecordInsulinReminder) {
        viewModelScope.launch {
            markInsulinReminderAsDoneUseCase(reminder)
        }
    }
}

