package com.ca.editglucosereminderbottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.RecordGlucoseReminder
import com.ca.domain.repository.RemindersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlucoseReminderBottomSheetViewModel @Inject constructor(
    private val remindersRepository: RemindersRepository
) : ViewModel() {
    
    private val _reminder = MutableStateFlow<RecordGlucoseReminder?>(null)
    val reminder = _reminder.asStateFlow()

    fun setReminder(id: Int) {
        viewModelScope.launch {
            val reminder = remindersRepository.glucoseReminderById(id)
            _reminder.update { reminder }
        }
    }

    fun removeReminder() {
        viewModelScope.launch { reminder.value?.let { remindersRepository.deleteGlucoseReminder(it) } }
    }

    fun turnOffReminder() {
        viewModelScope.launch {
            reminder.value?.let {
                remindersRepository.updateGlucoseReminder(it.copy(enabled = false))
            }
        }
    }

    fun turnOnReminder() {
        viewModelScope.launch {
            reminder.value?.let {
                remindersRepository.updateGlucoseReminder(it.copy(enabled = true))
            }
        }
    }
}