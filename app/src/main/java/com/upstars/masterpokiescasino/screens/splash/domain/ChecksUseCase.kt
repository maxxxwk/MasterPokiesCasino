package com.upstars.masterpokiescasino.screens.splash.domain

import com.upstars.masterpokiescasino.firebase.FirebaseRemoteConfigsRepository
import com.upstars.masterpokiescasino.screens.splash.data.SettingsRepository
import com.upstars.masterpokiescasino.screens.splash.data.SimCardAvailabilityRepository
import javax.inject.Inject
import java.util.Locale

class ChecksUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val simCardAvailabilityRepository: SimCardAvailabilityRepository,
    private val firebaseRemoteConfigsRepository: FirebaseRemoteConfigsRepository
) {
    operator fun invoke(): Boolean {
        return simCardAvailabilityRepository.isAvailable() &&
                !settingsRepository.isAirplaneModeOn() &&
                !settingsRepository.isDeveloperModeOn() &&
                !settingsRepository.isUSBDebuggingOn() &&
                Locale(firebaseRemoteConfigsRepository.deviceLanguage).language == Locale.getDefault().language &&
                firebaseRemoteConfigsRepository.isOfferEnabled
    }
}
