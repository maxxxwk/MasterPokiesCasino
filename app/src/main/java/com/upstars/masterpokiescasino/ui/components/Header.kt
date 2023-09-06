package com.upstars.masterpokiescasino.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.R

@Composable
fun Header(text: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
                .clickable(onClick = onBack),
            painter = painterResource(R.drawable.back),
            contentDescription = null
        )
        OutlinedGradientText(
            text = text,
            textSize = 24.sp
        )
    }
}
