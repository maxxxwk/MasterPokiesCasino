package com.upstars.masterpokiescasino.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.core.modifier.textBrush
import com.upstars.masterpokiescasino.ui.theme.casinoFlatFontFamily

@OptIn(ExperimentalTextApi::class)
@Suppress("MagicNumber")
@Composable
fun OutlinedGradientText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 16.sp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(top = 6.dp)
                .textBrush(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFFFF8DA),
                            Color(0xFFFBF3D4),
                            Color(0xFFFFFFFF),
                            Color(0xFFF4C60C),
                            Color(0xFFFFFFFF)
                        )
                    )
                ),
            text = text,
            fontFamily = casinoFlatFontFamily,
            fontSize = textSize
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = text,
            fontFamily = casinoFlatFontFamily,
            fontSize = textSize,
            color = Color.Black,
            style = TextStyle(
                drawStyle = Stroke(
                    width = 2f,
                    join = StrokeJoin.Round
                )
            )
        )
    }
}
