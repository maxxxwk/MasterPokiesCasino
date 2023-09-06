package com.upstars.masterpokiescasino.screens.slots.domain

import com.upstars.masterpokiescasino.preferences.PrizesRepository
import javax.inject.Inject

class AddCoinsUseCase @Inject constructor(
    private val prizesRepository: PrizesRepository
) {
    suspend operator fun invoke(count: Int) {
        prizesRepository.addCoins(count)
    }
}
