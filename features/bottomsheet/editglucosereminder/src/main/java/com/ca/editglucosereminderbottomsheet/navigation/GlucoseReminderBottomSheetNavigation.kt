package com.ca.editglucosereminderbottomsheet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ca.editglucosereminderbottomsheet.EditGlucoseReminderBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val REMINDER_ID = "reminderId"
const val glucoseReminderBottomSheetRoute = "edit_glucose_reminder_bottom_sheet_route"

fun NavController.navigateToGlucoseReminderBottomSheet(reminderId: Int) {
    navigate("$glucoseReminderBottomSheetRoute/$reminderId")
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.editGlucoseReminderBottomSheet(
    navigateToEditGlucoseReminder: (Int) -> Unit,
    dismiss: () -> Unit
) {
    bottomSheet(
        route = "$glucoseReminderBottomSheetRoute/{$REMINDER_ID}",
        arguments = listOf(navArgument(REMINDER_ID) { type = NavType.IntType })
    ) {
        val reminderIdArgument = it.arguments?.getInt(REMINDER_ID) ?: 0
        EditGlucoseReminderBottomSheet(
            reminderId = reminderIdArgument,
            navigateToGlucoseReminder = { navigateToEditGlucoseReminder(reminderIdArgument) },
            dismiss = dismiss
        )
    }
}