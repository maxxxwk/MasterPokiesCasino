package com.upstars.masterpokiescasino.screens.rouletteWheel.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.ui.components.GreenButton
import com.upstars.masterpokiescasino.ui.components.Prize

@Composable
fun RouletteWheelScreen(
    viewModel: RouletteWheelScreenViewModel,
    navigateToResultScreen: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.game_screen_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Prize(
            modifier = Modifier.height(48.dp),
            count = state.goldBars,
            prizeIconPainter = painterResource(R.drawable.gold_bars)
        )

        Spacer(modifier = Modifier.height(160.dp))

        RouletteWheel(
            modifier = Modifier.size(288.dp),
            isSpinning = state.isSpinning,
            onFinished = { angle ->
                val selectedSector = when (360 - angle.toInt()) {
                    in 0..15, in 346..360 -> 1
                    in 16..45 -> 2
                    in 46..75 -> 3
                    in 76..105 -> 4
                    in 106..135 -> 5
                    in 136..165 -> 6
                    in 166..195 -> 7
                    in 196..225 -> 8
                    in 226..255 -> 9
                    in 256..285 -> 10
                    in 286..315 -> 11
                    else -> 12
                }
                val prize = rouletteWheelPrizes[selectedSector - 1].prize
                viewModel.stopSpinning()
                viewModel.addGoldBars(prize)
                navigateToResultScreen(prize)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        GreenButton(
            modifier = Modifier
                .width(320.dp)
                .height(160.dp),
            text = stringResource(R.string.spin_button_text),
            onClick = { if (!state.isSpinning) viewModel.startSpinning() },
            textSize = 32.sp
        )
    }
}
