package com.ca.reminders.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinReminderCard
import com.ca.model.RecordInsulinReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsulinRemindersPage(
    reminders: List<RecordInsulinReminder>,
    onEnabledChange: (RecordInsulinReminder, Boolean) -> Unit,
    onClick: (RecordInsulinReminder) -> Unit
) {
    if(reminders.isEmpty()) {
        EmptyReminderScreen(modifier = Modifier)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            reminders.size
        ) {
            val reminder = reminders[it]
            InsulinReminderCard(
                modifier = Modifier,
                reminder = reminders[it],
                onCheckedChanged = { enabled: Boolean ->
                    onEnabledChange(reminder, enabled)
                },
                onClick = { onClick(reminder) }
            )
        }
        item { 
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp))
        }
    }
}