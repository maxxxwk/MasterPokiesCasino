package com.upstars.masterpokiescasino.screens.splash.presentation

sealed interface SplashScreenIntent {
    data object StartChecks : SplashScreenIntent
    data object ShowConnectionError : SplashScreenIntent
    data object NavigateToMain : SplashScreenIntent
    data object NavigateToWebview : SplashScreenIntent
    data object NavigationEventConsumed : SplashScreenIntent
}
