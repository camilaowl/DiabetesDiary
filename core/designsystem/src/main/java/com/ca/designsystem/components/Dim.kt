package com.ca.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Dim(
    modifier: Modifier,
    show: MutableState<Boolean>
) {
    val color = animateColorAsState(
        targetValue = if (show.value) Color.Black.copy(alpha = 0.5f) else Color.Transparent,
        label = ""
    )

    if (show.value) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color.value)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {  show.value = false}
                )
        )
    }
}