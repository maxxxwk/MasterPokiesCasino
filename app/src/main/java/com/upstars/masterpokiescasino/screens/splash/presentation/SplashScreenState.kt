package com.upstars.masterpokiescasino.screens.splash.presentation

import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

sealed interface SplashScreenState {

    data class Loading(
        val navigateToNextScreen: StateEventWithContent<NextScreen> = consumed()
    ) : SplashScreenState

    data object ConnectionError : SplashScreenState

    enum class NextScreen { MAIN, WEBVIEW }
}
