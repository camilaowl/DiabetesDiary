package com.ca.editglucoserecordbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.R
import com.ca.designsystem.components.BottomSheetContainer
import com.ca.designsystem.components.BottomSheetMenuOption

@Composable
fun EditGlucoseRecordBottomSheet(
    recordId: String,
    navigateToEditGlucoseRecord: () -> Unit,
    viewModel: GlucoseRecordBottomSheetViewModel = hiltViewModel(),
    show: MutableState<Boolean> = remember { mutableStateOf(true) },
) {
    if (show.value) {
        LaunchedEffect(true) {
            viewModel.setRecord(recordId)
        }

        val record by viewModel.record.collectAsStateWithLifecycle()

        BottomSheetContainer {
            if (record != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    BottomSheetMenuOption(icon = R.drawable.round_edit, title = stringResource(id = R.string.edit)) {
                        navigateToEditGlucoseRecord()
                    }

                    BottomSheetMenuOption(icon = R.drawable.round_delete, title = stringResource(id = R.string.delete)) {
                        viewModel.removeRecord()
                        show.value = false
                    }
                }
            }
        }
    }
}