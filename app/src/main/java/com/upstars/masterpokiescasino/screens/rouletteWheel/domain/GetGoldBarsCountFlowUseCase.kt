package com.upstars.masterpokiescasino.screens.rouletteWheel.domain

import com.upstars.masterpokiescasino.preferences.PrizesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGoldBarsCountFlowUseCase @Inject constructor(
    private val prizesRepository: PrizesRepository
) {
    operator fun invoke(): Flow<Int> =
        prizesRepository.getPrizesFlow().map { it.goldBars }
}
