package com.upstars.masterpokiescasino.screens.rouletteWheel.domain

import com.upstars.masterpokiescasino.preferences.PrizesRepository
import javax.inject.Inject

class AddGoldBarsUseCase @Inject constructor(
    private val prizesRepository: PrizesRepository
) {
    suspend operator fun invoke(count: Int) {
        prizesRepository.addGoldBars(count)
    }
}
