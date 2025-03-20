package com.ca.diabetesdiary.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ca.diabetesdiary.navigation.MainNavHost
import com.ca.diabetesdiary.presentation.state.DiaryAppState
import com.ca.navigation.nav_graphs.TopLevelDestination

@Composable
fun DiaryApp(
    appState: DiaryAppState,
    startDestination: TopLevelDestination
) {
    Scaffold { contentPadding ->
        MainNavHost(
            navHostController = appState.navController,
            startDestination = startDestination,
            modifier = Modifier.padding(contentPadding)
        )
    }
}