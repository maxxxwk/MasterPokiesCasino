package com.upstars.masterpokiescasino.screens.rouletteWheel.presentation

sealed interface RouletteWheelScreeIntent {
    data class UpdateGoldBarsCount(val count: Int) : RouletteWheelScreeIntent
    data object StartSpinning : RouletteWheelScreeIntent
    data object StopSpinning : RouletteWheelScreeIntent
    data class AddGoldBars(val count: Int) : RouletteWheelScreeIntent
}
