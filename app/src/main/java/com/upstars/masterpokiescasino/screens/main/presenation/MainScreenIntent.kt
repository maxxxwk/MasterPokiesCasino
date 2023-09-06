package com.upstars.masterpokiescasino.screens.main.presenation

import com.upstars.masterpokiescasino.screens.main.domain.Prizes

sealed interface MainScreenIntent {
    data class UpdatePrizes(val prizes: Prizes) : MainScreenIntent
}
