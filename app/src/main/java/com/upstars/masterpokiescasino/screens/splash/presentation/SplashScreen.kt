@file:Suppress("MagicNumber", "FunctionNaming")

package com.upstars.masterpokiescasino.screens.splash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.core.modifier.textBrush
import com.upstars.masterpokiescasino.ui.components.CustomProgressBar
import com.upstars.masterpokiescasino.ui.components.GreenButton
import com.upstars.masterpokiescasino.ui.theme.casinoFlatFontFamily
import de.palm.composestateevents.EventEffect

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navigateToMainScreen: () -> Unit,
    navigateToWebviewScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(
                    R.drawable.splash_screen_background
                ),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        when (state) {
            SplashScreenState.ConnectionError -> {
                ConnectionError(viewModel::retry)
            }

            is SplashScreenState.Loading -> {
                EventEffect(
                    event = (state as SplashScreenState.Loading).navigateToNextScreen,
                    onConsumed = viewModel::navigationEventConsumed,
                    action = {
                        when (it) {
                            SplashScreenState.NextScreen.MAIN -> navigateToMainScreen()
                            SplashScreenState.NextScreen.WEBVIEW -> navigateToWebviewScreen()
                        }
                    }
                )
                Loader()
            }
        }
        Spacer(modifier = Modifier.height(128.dp))
    }

}

@Composable
private fun Loader() {
    CustomProgressBar(modifier = Modifier.size(40.dp))
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        modifier = Modifier.textBrush(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFDEC80),
                    Color(0xFFAF6936),
                    Color(0xFFF1AA3D),
                    Color(0xFFFFF06F),
                    Color(0xFFFFFFFF),
                    Color(0xFFFDED77),
                    Color(0xFFE7C764),
                    Color(0xFFF1AA3D),
                    Color(0xFFC99141),
                    Color(0xFFAD7930)
                )
            )
        ),
        text = stringResource(R.string.loading_label),
        fontFamily = casinoFlatFontFamily,
        fontSize = 20.sp
    )
}

@Composable
private fun ConnectionError(retry: () -> Unit) {
    Text(
        text = stringResource(R.string.connection_error_text_message),
        fontSize = 32.sp,
        fontFamily = casinoFlatFontFamily,
        color = Color.White
    )
    Spacer(modifier = Modifier.height(16.dp))
    GreenButton(
        modifier = Modifier.width(240.dp),
        text = stringResource(R.string.retry_button_text),
        onClick = retry
    )
}
