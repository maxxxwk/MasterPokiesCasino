package com.upstars.masterpokiescasino.screens.slots.presentation

sealed interface SlotsScreenIntent {
    data class UpdateCoins(val count: Int) : SlotsScreenIntent
    data object StartSpinning : SlotsScreenIntent
    data object StopSpinning : SlotsScreenIntent
    data class AddCoins(val count: Int) : SlotsScreenIntent
}
