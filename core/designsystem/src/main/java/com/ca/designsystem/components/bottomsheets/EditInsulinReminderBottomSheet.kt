package com.ca.designsystem.components.bottomsheets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInsulinReminderBottomSheet(
    show: MutableState<Boolean>,
    deleteReminder: () -> Unit,
    turnOn: () -> Unit,
    turnOff: () -> Unit
) {

    if (show.value) {
        ModalBottomSheet(
            onDismissRequest = { show.value = false }
        ) {

        }
    }
}