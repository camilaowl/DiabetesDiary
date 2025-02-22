package com.ca.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ca.designsystem.components.Dim
import com.ca.designsystem.components.GlucoseRecordTimelineCard
import com.ca.designsystem.components.GlucoseReminderTimelineCard
import com.ca.designsystem.components.InsulinRecordTimelineCard
import com.ca.designsystem.components.InsulinReminderTimelineCard
import com.ca.designsystem.components.fab.NewRecordFab
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.home.presentation.viewmodel.HomeEvent
import com.ca.home.presentation.viewmodel.HomeViewState
import com.ca.model.GlucoseRecord
import com.ca.model.InsulinRecord
import com.ca.model.Record
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.Reminder
import java.time.LocalDate

@Composable
fun HomeContent(
    viewState: HomeViewState,
    addGlucoseRecord: () -> Unit,
    addInsulinRecord: () -> Unit,
    selectDate: (LocalDate) -> Unit,
    editInsulinReminder: (Int) -> Unit,
    editGlucoseReminder: (Int) -> Unit,
    editInsulinRecord: (String) -> Unit,
    editGlucoseRecord: (String) -> Unit
) {
    val context = LocalContext.current
    val focusRequester = FocusRequester()
    val fabExpanded = remember { mutableStateOf(false) }

    fun currentLocale() = context.resources.configuration.locales[0]

    Scaffold(
        floatingActionButton = {
            NewRecordFab(
                modifier = Modifier
                    .zIndex(2f),
                expanded = fabExpanded,
                addGlucoseMeasuring = { addGlucoseRecord() },
                addTakingInsulin = { addInsulinRecord() }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddings ->
        if (fabExpanded.value) {
            Dim(
                modifier = Modifier
                    .zIndex(1f),
                show = fabExpanded
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleRowCalendar(
                selectedDay = viewState.selectedDate,
                onSelectedDayChange = { selectDate(it) },
                locale = currentLocale()
            )
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Reminders(
                        modifier = Modifier,
                        reminders = viewState.reminders,
                        editInsulinReminder = { editInsulinReminder(it) },
                        editGlucoseReminder = { editGlucoseReminder(it) },
                        onDoneInsulin = {},
                        onDoneGlucose = {}
                    )
                }

                item {
                    Records(
                        modifier = Modifier,
                        records = viewState.recordsByDate,
                        editInsulinRecord = { editInsulinRecord(it) },
                        editGlucoseRecord = { editGlucoseRecord(it) }
                    )
                }

                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(76.dp))
                }
            }
        }
    }


}

@Composable
fun Reminders(
    modifier: Modifier,
    reminders: List<Reminder>,
    editInsulinReminder: (Int) -> Unit,
    editGlucoseReminder: (Int) -> Unit,
    onDoneInsulin: (Int) -> Unit,
    onDoneGlucose: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        reminders.forEach { reminder ->
            when(reminder) {
                is RecordInsulinReminder -> {
                    InsulinReminderTimelineCard(
                        reminder = reminder,
                        onDoneClick = { onDoneInsulin(reminder.id) },
                        onClick = { editInsulinReminder(reminder.id) },
                    )
                }
                is RecordGlucoseReminder -> {
                    GlucoseReminderTimelineCard(
                        reminder = reminder,
                        onAddClick = { onDoneGlucose(reminder.id) },
                        onClick = { editGlucoseReminder(reminder.id) },
                    )
                }
            }
        }
    }
}

@Composable
fun Records(
    modifier: Modifier,
    records: List<Record>,
    editInsulinRecord: (String) -> Unit,
    editGlucoseRecord: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        records.forEach { record ->
            when(record) {
                is InsulinRecord -> {
                    InsulinRecordTimelineCard(
                        record = record,
                        onClick = { editInsulinRecord(record.id) },
                    )
                }
                is GlucoseRecord -> {
                    GlucoseRecordTimelineCard(
                        record = record,
                        onClick = { editGlucoseRecord(record.id) },
                    )
                }
            }
        }
    }
}
