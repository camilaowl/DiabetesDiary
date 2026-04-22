package com.ca.editglucoserecordbottomsheet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ca.editglucoserecordbottomsheet.EditGlucoseRecordBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet


const val RECORD_ID = "recordId"
const val glucoseRecordBottomSheetRoute = "edit_glucose_record_bottom_sheet_route"

fun NavController.navigateToGlucoseRecordBottomSheet(recordId: String) {
    navigate("$glucoseRecordBottomSheetRoute/$recordId")
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.editGlucoseRecordBottomSheet(
    navigateToEditGlucoseRecord: (String) -> Unit,
    dismiss: () -> Unit
) {
    bottomSheet(
        route = "$glucoseRecordBottomSheetRoute/{$RECORD_ID}",
        arguments = listOf(navArgument(RECORD_ID) { type = NavType.StringType })
    ) {
        val recordIdArgument = it.arguments?.getString(RECORD_ID) ?: ""
        EditGlucoseRecordBottomSheet(
            recordId = recordIdArgument,
            navigateToEditGlucoseRecord = { navigateToEditGlucoseRecord(recordIdArgument) },
        )
    }
}