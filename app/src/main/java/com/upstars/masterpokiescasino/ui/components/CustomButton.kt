package com.upstars.masterpokiescasino.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.R

@Composable
fun GreenButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    textSize: TextUnit = 24.sp
) {
    CustomButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = painterResource(R.drawable.green_button_background),
        textSize = textSize
    )
}

@Composable
fun PinkButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    textSize: TextUnit = 24.sp
) {
    CustomButton(
        modifier = modifier,
        text = text,
        onClick = onClick,
        background = painterResource(R.drawable.pink_button_background),
        textSize = textSize
    )
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    background: Painter,
    textSize: TextUnit = 24.sp
) {
    OutlinedGradientText(
        modifier = modifier
            .paint(
                painter = background,
                contentScale = ContentScale.FillBounds
            )
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ),
        text = text,
        textSize = textSize
    )
}

@Composable
@Preview(showBackground = true)
fun GreenButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreenButton(
            text = "SLOT MACHINES"
        )
        PinkButton(
            text = "ROULETTE WHEELS"
        )
    }

}
