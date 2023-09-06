@file:Suppress("MagicNumber")

package com.upstars.masterpokiescasino.screens.slots.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.upstars.masterpokiescasino.R

@Composable
fun SlotMachine(
    modifier: Modifier = Modifier,
    isSpinning: Boolean,
    onFinished: () -> Unit,
    combination: Triple<SlotMachineItem, SlotMachineItem, SlotMachineItem>
) {
    Row(
        modifier = modifier.paint(
            painter = painterResource(R.drawable.slot_machine)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        SlotItem(
            modifier = Modifier
                .height(90.dp)
                .weight(1f),
            targetState = combination.first,
            initialState = SlotMachineItem.CROWN,
            isSpinning = isSpinning,
            rotationCount = 10
        )
        Spacer(modifier = Modifier.weight(0.25f))
        SlotItem(
            modifier = Modifier
                .height(90.dp)
                .weight(1f),
            targetState = combination.second,
            initialState = SlotMachineItem.CROWN,
            isSpinning = isSpinning,
            rotationCount = 12
        )
        Spacer(modifier = Modifier.weight(0.25f))
        SlotItem(
            modifier = Modifier
                .height(90.dp)
                .weight(1f),
            targetState = combination.third,
            initialState = SlotMachineItem.CROWN,
            isSpinning = isSpinning,
            rotationCount = 14,
            onFinished = onFinished
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Suppress("LongParameterList")
@Composable
private fun SlotItem(
    modifier: Modifier = Modifier,
    targetState: SlotMachineItem,
    initialState: SlotMachineItem,
    isSpinning: Boolean,
    rotationCount: Int,
    onFinished: (() -> Unit)? = null
) {
    val animation by animateIntAsState(
        targetValue = if (isSpinning) {
            SlotMachineItem.entries.size * rotationCount + targetState.ordinal
        } else {
            0
        },
        animationSpec = tween(
            durationMillis = (rotationCount + targetState.ordinal / SlotMachineItem.entries.size) * 400,
            easing = LinearEasing
        ),
        finishedListener = { onFinished?.invoke() }
    )

    val currentState = remember(animation) {
        if (!isSpinning) initialState
        else SlotMachineItem.entries[animation % 3]
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                slideInVertically(
                    animationSpec = tween(200),
                    initialOffsetY = { -it }
                ) with slideOutVertically(
                    animationSpec = tween(200),
                    targetOffsetY = { it }
                )
            }
        ) {
            Image(
                painter = painterResource(
                    id = when (it) {
                        SlotMachineItem.KEY -> R.drawable.key
                        SlotMachineItem.CROWN -> R.drawable.crown
                        SlotMachineItem.BELL -> R.drawable.bell
                    }
                ),
                contentDescription = null
            )
        }
    }
}
