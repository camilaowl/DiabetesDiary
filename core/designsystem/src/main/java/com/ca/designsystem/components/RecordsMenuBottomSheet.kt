package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme

@Composable
fun RecordsMenuBottomSheet(
    navigateToRecordGlucose: () -> Unit,
    navigateToRecordInsulin: () -> Unit,
) {
    BottomSheetContainer {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.LightGray, Theme.shapes.large)
                    .aspectRatio(1f)
                    .clickable { navigateToRecordGlucose() }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.glucose),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.LightGray, Theme.shapes.large)
                    .aspectRatio(1f)
                    .clickable { navigateToRecordInsulin() }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.vaccines),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}