package com.upstars.masterpokiescasino.screens.webview.domain

import com.upstars.masterpokiescasino.firebase.FirebaseRemoteConfigsRepository
import javax.inject.Inject

class GetURLUseCase @Inject constructor(
    private val firebaseRemoteConfigsRepository: FirebaseRemoteConfigsRepository
) {
    operator fun invoke() = firebaseRemoteConfigsRepository.offerURL
}
