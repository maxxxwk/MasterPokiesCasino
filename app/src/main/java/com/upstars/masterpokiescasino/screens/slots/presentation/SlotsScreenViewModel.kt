package com.upstars.masterpokiescasino.screens.slots.presentation

import androidx.lifecycle.viewModelScope
import com.upstars.masterpokiescasino.core.viewmodel.BaseViewModel
import com.upstars.masterpokiescasino.screens.slots.domain.AddCoinsUseCase
import com.upstars.masterpokiescasino.screens.slots.domain.GetCoinsCountFlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SlotsScreenViewModel @Inject constructor(
    private val addCoinsUseCase: AddCoinsUseCase,
    private val getCoinsCountFlowUseCase: GetCoinsCountFlowUseCase
) : BaseViewModel<SlotsScreenState, SlotsScreenIntent>(SlotsScreenState()) {

    init {
        getCoinsCountFlowUseCase()
            .onEach {
                intents.send(SlotsScreenIntent.UpdateCoins(it))
            }.launchIn(viewModelScope)
    }

    fun startSpinning() {
        viewModelScope.launch {
            intents.send(SlotsScreenIntent.StartSpinning)
        }
    }

    fun stopSpinning() {
        viewModelScope.launch {
            intents.send(SlotsScreenIntent.StopSpinning)
        }
    }

    fun addCoins(count: Int) {
        viewModelScope.launch {
            intents.send(SlotsScreenIntent.AddCoins(count))
        }
    }

    override fun reduce(intent: SlotsScreenIntent): SlotsScreenState = when (intent) {
        is SlotsScreenIntent.AddCoins -> {
            viewModelScope.launch { addCoinsUseCase(intent.count) }
            state.value
        }

        SlotsScreenIntent.StartSpinning -> state.value.copy(isSpinning = true)
        SlotsScreenIntent.StopSpinning -> state.value.copy(isSpinning = false)
        is SlotsScreenIntent.UpdateCoins -> state.value.copy(coins = intent.count)
    }

}
