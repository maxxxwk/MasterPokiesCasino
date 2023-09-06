package com.upstars.masterpokiescasino.screens.privacyPolicy.domain

import com.upstars.masterpokiescasino.firebase.FirebaseRemoteConfigsRepository
import javax.inject.Inject

class GetPrivacyPolicyUseCase @Inject constructor(
    private val firebaseRemoteConfigsRepository: FirebaseRemoteConfigsRepository
) {
    operator fun invoke() = firebaseRemoteConfigsRepository.privacyPolicy
}
