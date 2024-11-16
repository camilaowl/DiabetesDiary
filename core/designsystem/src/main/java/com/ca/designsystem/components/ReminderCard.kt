package com.ca.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun ReminderCard(
    backgroundColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = Theme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Theme.elevations.default
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        content = { content() }
    )
}