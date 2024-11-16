package com.ca.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@Composable
fun GlucoseReminderTimelineCard(
    reminder: RecordGlucoseReminder,
    onAddClick: (RecordGlucoseReminder) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = reminder.time.toString(),
            style = Theme.typography.bodyLarge
        )

        ReminderCard(
            backgroundColor = Theme.colors.background,
            onClick = onClick
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.reminder_glucose_measuring),
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(2f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilledIcon(
                            backgroundColor = Grey100,
                            icon = R.drawable.blood_filled)

                        Text(
                            text = stringResource(id = R.string.glucose_measuring),
                            style = Theme.typography.bodyLarge
                        )
                    }

                    TextButton(onClick = {
                        onAddClick(reminder)
                    }
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    }
}