package com.upstars.masterpokiescasino.screens.main.presenation

import androidx.lifecycle.viewModelScope
import com.upstars.masterpokiescasino.core.viewmodel.BaseViewModel
import com.upstars.masterpokiescasino.preferences.PrizesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainScreenViewModel @Inject constructor(
    prizesRepository: PrizesRepository
) : BaseViewModel<MainScreenState, MainScreenIntent>(MainScreenState()) {

    init {
        prizesRepository.getPrizesFlow()
            .onEach { intents.send(MainScreenIntent.UpdatePrizes(it)) }
            .launchIn(viewModelScope)
    }

    override fun reduce(intent: MainScreenIntent): MainScreenState = when (intent) {
        is MainScreenIntent.UpdatePrizes -> state.value.copy(
            prizes = intent.prizes
        )
    }
}
