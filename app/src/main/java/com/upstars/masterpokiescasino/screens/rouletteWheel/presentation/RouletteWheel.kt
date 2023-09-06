package com.upstars.masterpokiescasino.screens.rouletteWheel.presentation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.upstars.masterpokiescasino.R
import java.util.Random


@Composable
@Suppress("MagicNumber")
fun RouletteWheel(
    modifier: Modifier = Modifier,
    isSpinning: Boolean,
    onFinished: (Float) -> Unit
) {
    val rotationsCount = remember { 8f + Random().nextFloat() }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isSpinning) 360f * rotationsCount else 0f,
        animationSpec = tween(
            durationMillis = 4000,
            easing = CubicBezierEasing(0f, 1f, 0.3f, 1f)
        ),
        finishedListener = { onFinished(it - 360f * 8) }
    )
    Box {
        Image(
            modifier = modifier.rotate(rotationAngle),
            painter = painterResource(R.drawable.roulette_wheel),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .height(40.dp)
                .width(24.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(R.drawable.wheel_selector),
            contentDescription = null
        )
    }
}
