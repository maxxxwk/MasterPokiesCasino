package com.upstars.masterpokiescasino.screens.slots.domain

import com.upstars.masterpokiescasino.preferences.PrizesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCoinsCountFlowUseCase @Inject constructor(
    private val prizesRepository: PrizesRepository
) {
    operator fun invoke(): Flow<Int> =
        prizesRepository.getPrizesFlow().map { it.coins }
}
