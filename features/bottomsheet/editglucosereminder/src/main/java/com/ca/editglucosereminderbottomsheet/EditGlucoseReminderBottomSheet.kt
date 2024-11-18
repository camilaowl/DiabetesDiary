package com.ca.editglucosereminderbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
fun EditGlucoseReminderBottomSheet(
    reminderId: Int,
    navigateToGlucoseReminder: () -> Unit,
    dismiss: () -> Unit,
    viewModel: GlucoseReminderBottomSheetViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.setReminder(reminderId)
    }

    val reminder by viewModel.reminder.collectAsStateWithLifecycle()
    
    BottomSheetContainer {
        if (reminder != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BottomSheetMenuOption(icon = R.drawable.round_delete, title = stringResource(id = R.string.delete)) {
                    viewModel.removeReminder()
                    dismiss()
                }
                BottomSheetMenuOption(icon = R.drawable.round_edit, title = stringResource(id = R.string.edit)) {
                    navigateToGlucoseReminder()
                }
                if (reminder?.enabled!!) {
                    BottomSheetMenuOption(icon = R.drawable.round_notifications_off, title = stringResource(
                        id = R.string.turn_off
                    )) {
                        viewModel.turnOffReminder()
                        dismiss()
                    }
                } else {
                    BottomSheetMenuOption(icon = R.drawable.round_notifications_active, title = stringResource(
                        id = R.string.turn_on
                    )) {
                        viewModel.turnOnReminder()
                        dismiss()
                    }
                }
            }
        }
    }
}