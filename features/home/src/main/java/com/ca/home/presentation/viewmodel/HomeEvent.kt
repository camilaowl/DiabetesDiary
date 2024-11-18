package com.ca.home.presentation.viewmodel

import java.time.LocalDate

sealed interface HomeEvent {
    data class SelectDate(val date: LocalDate) : HomeEvent
    data class FetchReminders(val date: LocalDate) : HomeEvent
    data class FetchRecordsByDate(val date: LocalDate) : HomeEvent
    data class EditGlucoseReminder(val id: Int) : HomeEvent
    data class EditInsulinReminder(val id: Int) : HomeEvent
    data class EditGlucoseRecord(val id: String) : HomeEvent
    data class EditInsulinRecord(val id: String) : HomeEvent
    data object AddGlucoseRecord : HomeEvent
    data object AddInsulinRecord : HomeEvent
}