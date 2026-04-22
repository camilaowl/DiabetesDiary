package com.ca.editinsulinreminderbottomsheet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ca.editinsulinreminderbottomsheet.EditRecordInsulinReminderBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val REMINDER_ID = "reminderId"
const val insulinReminderBottomSheetRoute = "edit_insulin_reminder_bottom_sheet_route"

fun NavController.navigateToInsulinReminderBottomSheet(reminderId: Int) {
    navigate("$insulinReminderBottomSheetRoute/$reminderId")
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.editInsulinReminderBottomSheet(
    navigateToEditInsulinReminder: (Int) -> Unit,
    dismiss: () -> Unit
) {
    bottomSheet(
        route = "$insulinReminderBottomSheetRoute/{$REMINDER_ID}",
        arguments = listOf(navArgument(REMINDER_ID) { type = NavType.IntType })
    ) {
        val reminderIdArgument = it.arguments?.getInt(REMINDER_ID) ?: 0
        EditRecordInsulinReminderBottomSheet(
            reminderId = reminderIdArgument,
            navigateToEditInsulinReminder = { navigateToEditInsulinReminder(reminderIdArgument) },
            dismiss = dismiss
        )
    }
}