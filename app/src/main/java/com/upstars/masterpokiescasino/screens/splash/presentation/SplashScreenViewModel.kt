package com.upstars.masterpokiescasino.screens.splash.presentation

import androidx.lifecycle.viewModelScope
import com.upstars.masterpokiescasino.core.viewmodel.BaseViewModel
import com.upstars.masterpokiescasino.screens.splash.data.InternetAvailabilityRepository
import com.upstars.masterpokiescasino.screens.splash.domain.ChecksUseCase
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import javax.inject.Inject
import kotlinx.coroutines.launch

class SplashScreenViewModel @Inject constructor(
    private val checksUseCase: ChecksUseCase,
    private val internetAvailabilityRepository: InternetAvailabilityRepository
) : BaseViewModel<SplashScreenState, SplashScreenIntent>(SplashScreenState.Loading()) {

    init {
        startChecks()
    }

    fun retry() {
        viewModelScope.launch {
            intents.send(SplashScreenIntent.StartChecks)
        }
    }

    fun navigationEventConsumed() {
        viewModelScope.launch {
            intents.send(SplashScreenIntent.NavigationEventConsumed)
        }
    }

    override fun reduce(intent: SplashScreenIntent): SplashScreenState = when (intent) {
        SplashScreenIntent.NavigateToMain -> SplashScreenState.Loading(
            navigateToNextScreen = triggered(SplashScreenState.NextScreen.MAIN)
        )

        SplashScreenIntent.NavigateToWebview -> SplashScreenState.Loading(
            navigateToNextScreen = triggered(SplashScreenState.NextScreen.WEBVIEW)
        )

        SplashScreenIntent.NavigationEventConsumed -> SplashScreenState.Loading(
            navigateToNextScreen = consumed()
        )

        SplashScreenIntent.ShowConnectionError -> SplashScreenState.ConnectionError
        SplashScreenIntent.StartChecks -> {
            startChecks()
            SplashScreenState.Loading()
        }
    }

    private fun startChecks() {
        viewModelScope.launch {
            if (internetAvailabilityRepository.isAvailable()) {
                if (checksUseCase()) {
                    intents.send(SplashScreenIntent.NavigateToWebview)
                } else {
                    intents.send(SplashScreenIntent.NavigateToMain)
                }
            } else {
                intents.send(SplashScreenIntent.ShowConnectionError)
            }
        }
    }
}
