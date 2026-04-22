package com.ca.recordinsulin.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.common.utils.date
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.R
import com.ca.designsystem.components.*
import com.ca.designsystem.components.pickers.DatePicker
import com.ca.designsystem.components.pickers.TimePicker
import com.ca.designsystem.components.topbar.TopBar
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun RecordInsulinRoute(
    viewModel: RecordInsulinViewModel = hiltViewModel(),
    recordId: String?,
    onBackClick: () -> Unit,
    navigateToSettings: () -> Unit
) {
    LaunchedEffect(Unit) {
        if (recordId == null) return@LaunchedEffect
        viewModel.setupEditMode(recordId)
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    RecordInsulinScreen(
        topBarTitle = if (viewState.isInEditMode) stringResource(id = R.string.edit_record) else stringResource(id = R.string.add_record),
        onBackClick = onBackClick,
        navigateToSettings = navigateToSettings,
        viewState = viewState,
        showTimePicker = viewModel::showTimePicker,
        setTime = viewModel::setTime,
        showDatePicker = viewModel::showDatePicker,
        setDate = viewModel::setDate,
        setInsulinDropDownMenuExpanded = viewModel::setInsulinDropDownMenuExpanded,
        selectInsulin = viewModel::selectInsulin,
        incrementUnits = viewModel::incrementUnits,
        decrementUnits = viewModel::decrementUnits,
        setUnits = viewModel::setUnits,
        setNote = viewModel::setNote,
        submit = if (viewState.isInEditMode) viewModel::updateRecord else viewModel::addRecord
    )
}

@Composable
fun RecordInsulinScreen(
    topBarTitle: String,
    onBackClick: () -> Unit,
    navigateToSettings: () -> Unit,
    viewState: RecordInsulinViewState,
    showTimePicker: (Boolean) -> Unit,
    setTime: (LocalTime) -> Unit,
    showDatePicker: (Boolean) -> Unit,
    setDate: (LocalDate) -> Unit,
    setInsulinDropDownMenuExpanded: (Boolean) -> Unit,
    selectInsulin: (Insulin) -> Unit,
    incrementUnits: () -> Unit,
    decrementUnits: () -> Unit,
    setUnits: (String) -> Unit,
    setNote: (String) -> Unit,
    submit: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->

        TimePicker(
            expanded = viewState.showTimePicker,
            onDismiss = { showTimePicker(false) },
            time = viewState.time,
            setTime = { setTime(it) }
        )

        DatePicker(
            expanded = viewState.showDatePicker,
            onDismiss = { showDatePicker(false) },
            date = viewState.date,
            setDate = { setDate(it) }
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus(true) },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DateCard(
                            modifier = Modifier,
                            date = viewState.date.date(),
                            onClick = { showDatePicker(true) }
                        )
                        TimeCard(
                            modifier = Modifier,
                            time = viewState.time.timeOfHHmmPattern(),
                            onClick = { showTimePicker(true) }
                        )
                    }
                }

                item {
                    if (viewState.insulins.isEmpty()) {
                        AddInsulinButton { navigateToSettings() }
                    } else {
                        InsulinDropDownMenu(
                            modifier = Modifier
                                .fillMaxWidth(),
                            expanded = viewState.insulinDropDownMenuExpanded,
                            onExpandedChange = { setInsulinDropDownMenuExpanded(!viewState.insulinDropDownMenuExpanded) },
                            onSelect = { selectInsulin(it) },
                            onDismiss = { setInsulinDropDownMenuExpanded(false) },
                            selectedInsulin = viewState.selectedInsulin!!,
                            options = viewState.insulins
                        )
                    }
                }

                item {
                    Counter(
                        modifier = Modifier,
                        value = viewState.units,
                        increment = { incrementUnits() },
                        decrement = { decrementUnits() },
                        onValueChanged = { setUnits(it) }
                    )
                }

                item {
                    NoteTextField(
                        value = viewState.note,
                        onValueChange = { setNote(it) },
                        modifier = Modifier,
                        expanded = viewState.noteTextFieldExpanded,
                        placeholder = { Text(text = stringResource(id = R.string.type_note), color = Color.Gray) },
                        onDoneAction = {
                            setNote(it)
                            keyboardController?.hide()
                            focusManager.clearFocus(true)
                        },
                        expandedMaxLines = 10,
                        collapsedMaxLines = 5
                    )
                }
            }

            Button(
                onClick = {
                    submit()
                    onBackClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Theme.colors.secondary
                ),
                shape = Theme.shapes.large,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = Theme.colors.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun RecordInsulinScreenPreview() {
    DiaryTheme {
        RecordInsulinScreen(
            topBarTitle = "Record Insulin",
            onBackClick = {},
            viewState = RecordInsulinViewState(),
            showTimePicker = {},
            showDatePicker = {},
            setDate = {_->},
            setNote = {},
            setTime = {},
            selectInsulin = {},
            setUnits = {},
            setInsulinDropDownMenuExpanded = {},
            navigateToSettings = {},
            decrementUnits = {},
            incrementUnits = {},
            submit = {}
        )
    }
}