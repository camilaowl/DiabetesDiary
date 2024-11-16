package com.ca.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinReminderTimelineCard(
    reminder: RecordInsulinReminder,
    onDoneClick: (RecordInsulinReminder) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
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
                    text = stringResource(id = R.string.reminder_taking_insulin),
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIcon(
                        backgroundColor = colorFromHex(reminder.insulin?.color!!),
                        icon = R.drawable.notifications
                    )

                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(2f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = reminder.insulin?.name!!,
                            style = Theme.typography.bodyLarge
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = reminder.dose.toString(),
                                style = Theme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.international_unit),
                                style = Theme.typography.bodySmall,
                            )
                        }
                    }

                    TextButton(
                        onClick = { onDoneClick(reminder) }
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    }
}