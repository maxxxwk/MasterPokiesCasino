@file:Suppress("FunctionNaming")

package com.upstars.masterpokiescasino.screens.main.presenation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navigateToSlots: () -> Unit,
    navigateToRouletteWheel: () -> Unit,
    navigateToPrivacyPolicyScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DoubleBackClickForExit()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Prizes(
            coinsCount = state.prizes.coins,
            goldBarsCount = state.prizes.goldBars
        )
        Column {
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
        }
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
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

private const val DOUBLE_BACK_CLICK_TIME = 2000L

@Composable
private fun DoubleBackClickForExit() {
    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    BackHandler(!doubleBackToExitPressedOnce) { doubleBackToExitPressedOnce = true }

    val context = LocalContext.current
    LaunchedEffect(doubleBackToExitPressedOnce) {
        if (doubleBackToExitPressedOnce) {
            Toast.makeText(context, R.string.exit_toast, Toast.LENGTH_SHORT).show()
            delay(DOUBLE_BACK_CLICK_TIME)
            doubleBackToExitPressedOnce = false
        }
    }
}
