package com.upstars.masterpokiescasino.screens.rouletteWheel.presentation

import androidx.lifecycle.viewModelScope
import com.upstars.masterpokiescasino.core.viewmodel.BaseViewModel
import com.upstars.masterpokiescasino.screens.rouletteWheel.domain.AddGoldBarsUseCase
import com.upstars.masterpokiescasino.screens.rouletteWheel.domain.GetGoldBarsCountFlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RouletteWheelScreenViewModel @Inject constructor(
    getGoldBarsCountFlowUseCase: GetGoldBarsCountFlowUseCase,
    private val addGoldBarsUseCase: AddGoldBarsUseCase
) : BaseViewModel<RouletteWheelScreenState, RouletteWheelScreeIntent>(RouletteWheelScreenState()) {

    init {
        getGoldBarsCountFlowUseCase()
            .onEach {
                intents.send(RouletteWheelScreeIntent.UpdateGoldBarsCount(it))
            }.launchIn(viewModelScope)
    }

    fun startSpinning() {
        viewModelScope.launch {
            intents.send(RouletteWheelScreeIntent.StartSpinning)
        }
    }

    fun stopSpinning() {
        viewModelScope.launch {
            intents.send(RouletteWheelScreeIntent.StopSpinning)
        }
    }

    fun addGoldBars(count: Int) {
        viewModelScope.launch {
            intents.send(RouletteWheelScreeIntent.AddGoldBars(count))
        }
    }

    override fun reduce(intent: RouletteWheelScreeIntent): RouletteWheelScreenState =
        when (intent) {
            is RouletteWheelScreeIntent.AddGoldBars -> {
                viewModelScope.launch { addGoldBarsUseCase(intent.count) }
                state.value
            }

            RouletteWheelScreeIntent.StartSpinning -> state.value.copy(isSpinning = true)
            RouletteWheelScreeIntent.StopSpinning -> state.value.copy(isSpinning = false)
            is RouletteWheelScreeIntent.UpdateGoldBarsCount -> state.value.copy(goldBars = intent.count)
        }
}
