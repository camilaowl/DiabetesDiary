package com.ca.editglucoserecordbottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.GlucoseRecord
import com.ca.domain.repository.RecordGlucoseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlucoseRecordBottomSheetViewModel @Inject constructor(
    private val recordGlucoseRepository: RecordGlucoseRepository
) : ViewModel() {

    private val _record = MutableStateFlow<GlucoseRecord?>(null)
    val record = _record.asStateFlow()

    fun setRecord(id: String) {
        viewModelScope.launch {
            val reminder = recordGlucoseRepository.recordById(id)
            _record.update { reminder }
        }
    }

    fun removeRecord() {
        viewModelScope.launch { record.value?.let { recordGlucoseRepository.delete(it) } }
    }
}