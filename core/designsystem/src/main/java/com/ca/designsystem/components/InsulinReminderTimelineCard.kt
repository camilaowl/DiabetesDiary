package com.ca.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinReminderTimelineCard(
    reminder: RecordInsulinReminder,
    onDoneClick: (RecordInsulinReminder) -> Unit,
    onClick: () -> Unit
) {
    ReminderCard(
        backgroundColor = Theme.colors.background,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = reminder.time.toString(),
                style = Theme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = reminder.insulin?.name.orEmpty(),
                style = Theme.typography.bodyLarge
            )

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.international_unit, reminder.dose),
                    style = Theme.typography.bodySmall,
                )

                IconButton(
                    onClick = { onDoneClick(reminder) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        tint = Theme.colors.surface,
                        contentDescription = null
                    )
                }
            }
        }
    }
}