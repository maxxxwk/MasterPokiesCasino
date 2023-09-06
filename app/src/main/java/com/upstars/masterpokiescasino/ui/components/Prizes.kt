package com.upstars.masterpokiescasino.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.core.text.normalizeDigitsInCasinoFlatText
import com.upstars.masterpokiescasino.ui.theme.casinoFlatFontFamily


@Composable
fun Prizes(
    modifier: Modifier = Modifier,
    coinsCount: Int,
    goldBarsCount: Int
) {
    Row(
        modifier = modifier
            .paint(
                painter = painterResource(R.drawable.prizes_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrizeCount(
                count = coinsCount,
                prizeIconPainter = painterResource(R.drawable.coins)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrizeCount(
                count = goldBarsCount,
                prizeIconPainter = painterResource(R.drawable.gold_bars)
            )
        }
    }
}

@Composable
fun Prize(
    modifier: Modifier = Modifier,
    count: Int,
    prizeIconPainter: Painter
) {
    Row(
        modifier = modifier
            .paint(
                painter = painterResource(R.drawable.prize_background),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PrizeCount(count = count, prizeIconPainter = prizeIconPainter)
    }
}

@Composable
fun PrizeCount(count: Int, prizeIconPainter: Painter) {
    Image(
        modifier = Modifier.size(20.dp),
        painter = prizeIconPainter,
        contentDescription = null
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
        modifier = Modifier.padding(top = 2.dp),
        text = count.toString().normalizeDigitsInCasinoFlatText(),
        color = Color.White,
        fontFamily = casinoFlatFontFamily,
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.width(2.dp))
    Image(
        modifier = Modifier.size(12.dp),
        painter = painterResource(R.drawable.plus),
        contentDescription = null
    )
}

@Composable
@Preview(showBackground = true)
fun PrizesPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Prizes(
            coinsCount = 10000,
            goldBarsCount = 20000
        )
        Spacer(modifier = Modifier.height(8.dp))
        Prizes(
            coinsCount = 80,
            goldBarsCount = 20
        )
        Spacer(modifier = Modifier.height(8.dp))
        Prizes(
            coinsCount = 1000,
            goldBarsCount = 2000
        )
        Spacer(modifier = Modifier.height(8.dp))
        Prizes(
            coinsCount = 5,
            goldBarsCount = 0
        )
        Spacer(modifier = Modifier.height(8.dp))
        Prize(
            modifier = Modifier.width(300.dp),
            count = 110,
            prizeIconPainter = painterResource(R.drawable.coins)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Prize(
            modifier = Modifier.width(300.dp),
            count = 20,
            prizeIconPainter = painterResource(R.drawable.gold_bars)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
