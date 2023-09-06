package com.upstars.masterpokiescasino.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.upstars.masterpokiescasino.R

@Composable
@Preview(showBackground = true)
@Suppress("FunctionNaming")
fun CustomProgressBar(
    modifier: Modifier = Modifier,
) {
    val transition = rememberInfiniteTransition(label = "progress bar animation")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        ),
        label = "progress bar animation"
    )
    Image(
        modifier = modifier.rotate(angle),
        painter = painterResource(R.drawable.progress_bar),
        contentDescription = null
    )
}
