@file:Suppress("FunctionNaming")

package com.upstars.masterpokiescasino.screens.result.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.core.text.normalizeDigitsInCasinoFlatText
import com.upstars.masterpokiescasino.ui.components.GreenButton
import com.upstars.masterpokiescasino.ui.components.Header
import com.upstars.masterpokiescasino.ui.theme.casinoFlatFontFamily

@Composable
fun ResultScreen(
    count: Int,
    prizeType: PrizeType,
    onBack: () -> Unit,
    navigateToMainScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.result_screen_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(text = stringResource(R.string.result), onBack = onBack)
        PrizeResult(
            modifier = Modifier.height(48.dp),
            prizeType = prizeType,
            count = count
        )
        GreenButton(
            modifier = Modifier
                .width(288.dp)
                .height(80.dp),
            onClick = navigateToMainScreen,
            text = stringResource(R.string.play_more)
        )
    }
}

@Composable
private fun PrizeResult(
    modifier: Modifier = Modifier,
    prizeType: PrizeType,
    count: Int
) {
    Row(
        modifier = modifier
            .paint(
                painter = painterResource(R.drawable.prize_result_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(
                id = when (prizeType) {
                    PrizeType.COINS -> R.drawable.coins
                    PrizeType.GOLD_BARS -> R.drawable.gold_bars
                }
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = count.toString().normalizeDigitsInCasinoFlatText(),
            color = Color.White,
            fontFamily = casinoFlatFontFamily,
            fontSize = 24.sp
        )
    }
}
