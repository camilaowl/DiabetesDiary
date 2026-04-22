package com.ca.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.home.presentation.viewmodel.HomeViewModel

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
