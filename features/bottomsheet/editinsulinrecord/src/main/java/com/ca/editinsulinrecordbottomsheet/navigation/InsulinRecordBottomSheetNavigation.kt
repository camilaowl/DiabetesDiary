package com.ca.editinsulinrecordbottomsheet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ca.editinsulinrecordbottomsheet.EditInsulinRecordBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val RECORD_ID = "recordId"
const val insulinRecordBottomSheetRoute = "edit_insulin_record_bottom_sheet_route"

fun NavController.navigateToInsulinRecordBottomSheet(recordId: String) {
    navigate("$insulinRecordBottomSheetRoute/$recordId")
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.editInsulinRecordBottomSheet(
    navigateToEditInsulinRecord: (String) -> Unit,
    dismiss: () -> Unit
) {
    bottomSheet(
        route = "$insulinRecordBottomSheetRoute/{$RECORD_ID}",
        arguments = listOf(navArgument(RECORD_ID) { type = NavType.StringType })
    ) {
        val recordIdArgument = it.arguments?.getString(RECORD_ID) ?: ""
        EditInsulinRecordBottomSheet(
            recordId = recordIdArgument,
            navigateToEditInsulinRecord = { navigateToEditInsulinRecord(recordIdArgument) },
            dismiss = dismiss
        )
    }
}