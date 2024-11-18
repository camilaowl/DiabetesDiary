package com.ca.designsystem.components.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme

@Composable
fun NewRecordFab(
    expanded: MutableState<Boolean>,
    addTakingInsulin: () -> Unit = {},
    addGlucoseMeasuring: () -> Unit = {},
    modifier: Modifier
) {
    val fabSize = 56.dp
    val expandedFabWidth by animateDpAsState(
        targetValue = if (expanded.value) 200.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )
    val expandedFabHeight by animateDpAsState(
        targetValue = if (expanded.value) 56.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f)
    )

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .offset(y = 25.dp)
                .size(
                    width = expandedFabWidth,
                    height = (animateDpAsState(
                        if (expanded.value) 136.dp else 0.dp,
                        animationSpec = spring(dampingRatio = 4f)
                    )).value
                )
                .background(
                    Theme.colors.background,
                    shape = FloatingActionButtonDefaults.shape
                )
        ) {
            FabContent(
                expanded = expanded,
                addGlucoseMeasuring = addGlucoseMeasuring,
                addTakingInsulin = addTakingInsulin
            )
        }

        FloatingActionButton(
            onClick = {
                expanded.value = !expanded.value
            },
            modifier = Modifier
                .width(expandedFabWidth)
                .height(expandedFabHeight),
            containerColor = Theme.colors.secondary,
            contentColor = Theme.colors.onSecondary
        ) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .offset(
                        x = animateDpAsState(
                            if (expanded.value) (-70).dp else 0.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
                ,
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )

            Text(
                modifier = Modifier
                    .offset(
                        x = animateDpAsState(
                            if (expanded.value) 10.dp else 50.dp,
                            animationSpec = spring(dampingRatio = 3f)
                        ).value
                    )
                    .alpha(
                        animateFloatAsState(
                            targetValue = if (expanded.value) 1f else 0f,
                            animationSpec = tween(
                                durationMillis = if (expanded.value) 350 else 100,
                                delayMillis = if (expanded.value) 100 else 0,
                                easing = EaseIn
                            )
                        ).value
                    ),
                text = "Add Record",
                softWrap = false,
                style = Theme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun FabContent(
    expanded: MutableState<Boolean>,
    addGlucoseMeasuring: () -> Unit,
    addTakingInsulin: () -> Unit
) {
    AnimatedVisibility(
        visible = expanded.value,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = if (expanded.value) 350 else 100,
                delayMillis = if (expanded.value) 100 else 0,
                easing = EaseIn
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = if (expanded.value) 100 else 350,
                delayMillis = if (expanded.value) 0 else 100,
                easing = EaseOut
            )
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            FabItem(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                icon = painterResource(R.drawable.glucose),
                text = "Glucose Measuring",
                expanded = expanded,
                onClick = addGlucoseMeasuring
            )

            FabItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 24.dp),
                icon = painterResource(R.drawable.vaccines),
                text = "Taking Insulin",
                expanded = expanded,
                onClick = addTakingInsulin
            )
        }
    }
}

@Composable
private fun FabItem(
    modifier: Modifier,
    icon: Painter,
    text: String,
    expanded: MutableState<Boolean>,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(
                    animateDpAsState(
                        if (expanded.value) 24.dp else 6.dp,
                        animationSpec = spring(dampingRatio = 3f)
                    ).value
                ),
            painter = icon,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .weight(1f, true)
                .offset(
                    x = animateDpAsState(
                        if (expanded.value) 10.dp else 50.dp,
                        animationSpec = spring(dampingRatio = 3f)
                    ).value
                )
                .alpha(
                    animateFloatAsState(
                        targetValue = if (expanded.value) 1f else 0f,
                        animationSpec = tween(
                            durationMillis = if (expanded.value) 350 else 100,
                            delayMillis = if (expanded.value) 100 else 0,
                            easing = EaseIn
                        )
                    ).value
                ),
            text = text,
            style = Theme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun NewRecordFabPreview() {
    val expanded = remember { mutableStateOf(false) }

    DiaryTheme {
        Scaffold(
            floatingActionButton = {
                NewRecordFab(
                    modifier = Modifier,
                    expanded = expanded
                )
            },
            floatingActionButtonPosition = FabPosition.End,
        ) { paddings ->
            Box(
                modifier = Modifier
                    .padding(paddings)
            ) {

            }
        }
    }
}

