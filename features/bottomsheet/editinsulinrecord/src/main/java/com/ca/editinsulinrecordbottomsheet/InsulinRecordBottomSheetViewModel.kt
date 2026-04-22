package com.ca.editinsulinrecordbottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.InsulinRecord
import com.ca.domain.repository.RecordInsulinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsulinRecordBottomSheetViewModel @Inject constructor(
    private val recordInsulinRepository: RecordInsulinRepository
) : ViewModel() {

    private val _record = MutableStateFlow<InsulinRecord?>(null)
    val record = _record.asStateFlow()

    fun setRecord(id: String) {
        viewModelScope.launch {
            val reminder = recordInsulinRepository.recordById(id)
            _record.update { reminder }
        }
    }

    fun removeRecord() {
        viewModelScope.launch { record.value?.let { recordInsulinRepository.delete(it) } }
    }
}