package com.ca.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun BottomSheetContainer(
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .background(
                Theme.colors.surface,
                Theme.shapes.bottomSheet
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Canvas(
                modifier = Modifier
            ) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(-50f, 0f),
                    end = Offset(50f, 0f),
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round,
                    alpha = 0.3f
                )
            }
            content()
        }
    }
}

@Composable
fun BottomSheetMenuOption(
    icon: Int? = null,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        icon?.let { Icon(painter = painterResource(id = icon), contentDescription = "") }
        Text(text = title)
    }
}