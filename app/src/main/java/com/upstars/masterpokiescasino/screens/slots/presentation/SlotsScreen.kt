package com.upstars.masterpokiescasino.screens.slots.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.upstars.masterpokiescasino.ui.components.PinkButton
import com.upstars.masterpokiescasino.ui.components.Prize

@Composable
fun SlotsScreen(
    viewModel: SlotsScreenViewModel,
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
            count = state.coins,
            prizeIconPainter = painterResource(R.drawable.coins)
        )

        Spacer(modifier = Modifier.height(160.dp))

        var combination by remember {
            mutableStateOf(
                Triple(
                    SlotMachineItem.entries.random(),
                    SlotMachineItem.entries.random(),
                    SlotMachineItem.entries.random()
                )
            )
        }

        SlotMachine(
            modifier = Modifier.size(288.dp),
            isSpinning = state.isSpinning,
            onFinished = {
                viewModel.stopSpinning()
                val prize = getCoinsPrize(combination)
                combination = Triple(
                    SlotMachineItem.entries.random(),
                    SlotMachineItem.entries.random(),
                    SlotMachineItem.entries.random()
                )
                viewModel.addCoins(prize)
                navigateToResultScreen(prize)
            },
            combination = combination
        )

        Spacer(modifier = Modifier.height(16.dp))

        PinkButton(
            modifier = Modifier
                .width(320.dp)
                .height(160.dp),
            text = stringResource(R.string.spin_button_text),
            onClick = { if (!state.isSpinning) viewModel.startSpinning() },
            textSize = 32.sp
        )
    }
}
