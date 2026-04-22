package com.ca.diabetesdiary.navigation.bottombar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.components.topbar.MainTopBar
import com.ca.glucosereminder.navigation.navigateToGlucoseReminder
import com.ca.home.presentation.HomeScreen
import com.ca.insulinreminder.navigation.navigateToInsulinReminder
import com.ca.records.presentation.RecordsScreen
import com.ca.reminders.presentation.RemindersScreen
import com.ca.navigation.nav_graphs.MainGraph

@Composable
fun BottomBarMenuNavHost(
    mainNavController: NavHostController
) {
    val bottomMenuNavHostController = rememberNavController()

    Scaffold(
        topBar = {
             MainTopBar(title = "Diabetes Diary") {
                 mainNavController.navigate(MainGraph.Settings)
             }
        },
        bottomBar = { BottomBar(navController = bottomMenuNavHostController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomMenuNavHostController,
            startDestination = BottomBarRoute.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<BottomBarRoute.Home> {
                HomeScreen()
            }
            composable<BottomBarRoute.Records> {
                RecordsScreen()
            }
            composable<BottomBarRoute.Reminders> {
                RemindersScreen(
                    navigateToAddInsulinReminder = { mainNavController.navigateToInsulinReminder() },
                    navigateToAddGlucoseReminder = { mainNavController.navigateToGlucoseReminder() },
                )
            }
        }
    }
}