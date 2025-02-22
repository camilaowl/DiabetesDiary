package com.ca.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.GlucoseRecordTimelineCard
import com.ca.designsystem.components.GlucoseReminderTimelineCard
import com.ca.designsystem.components.InsulinRecordTimelineCard
import com.ca.designsystem.components.InsulinReminderTimelineCard
import com.ca.designsystem.components.fab.NewRecordFab
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.home.presentation.viewmodel.HomeViewModel
import com.ca.model.GlucoseRecord
import com.ca.model.InsulinRecord
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    HomeContent(
        viewState = viewState,
        addGlucoseRecord = {},
        addInsulinRecord = {},
        selectDate = viewModel::selectDate,
        editInsulinReminder = {},
        editGlucoseReminder = {},
        editInsulinRecord = {},
        editGlucoseRecord = {},
    )
}
