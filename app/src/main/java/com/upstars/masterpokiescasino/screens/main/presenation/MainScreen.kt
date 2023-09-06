package com.upstars.masterpokiescasino.screens.main.presenation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.ui.components.GreenButton
import com.upstars.masterpokiescasino.ui.components.OutlinedGradientText
import com.upstars.masterpokiescasino.ui.components.PinkButton
import com.upstars.masterpokiescasino.ui.components.Prizes

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navigateToSlots: () -> Unit,
    navigateToRouletteWheel: () -> Unit,
    navigateToPrivacyPolicyScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Prizes(
            coinsCount = state.prizes.coins,
            goldBarsCount = state.prizes.goldBars
        )
        Spacer(modifier = Modifier.height(144.dp))
        PinkButton(
            modifier = Modifier
                .width(320.dp)
                .height(96.dp),
            text = stringResource(R.string.slots_machines_button_text),
            onClick = navigateToSlots,
            textSize = 32.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        GreenButton(
            modifier = Modifier
                .width(320.dp)
                .height(96.dp),
            text = stringResource(R.string.roulette_wheel_button_text),
            onClick = navigateToRouletteWheel,
            textSize = 32.sp
        )
        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = navigateToPrivacyPolicyScreen
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.privacy_policy),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedGradientText(
                modifier = Modifier.padding(bottom = 4.dp),
                text = stringResource(R.string.privacy_policy),
                textSize = 24.sp
            )
        }
    }
}