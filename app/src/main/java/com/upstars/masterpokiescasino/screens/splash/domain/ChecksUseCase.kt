package com.upstars.masterpokiescasino.screens.splash.domain

import com.upstars.masterpokiescasino.firebase.FirebaseRemoteConfigsRepository
import com.upstars.masterpokiescasino.screens.splash.data.SettingsRepository
import com.upstars.masterpokiescasino.screens.splash.data.SimCardAvailabilityRepository
import javax.inject.Inject
import kotlinx.coroutines.delay
import java.util.Locale

class ChecksUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val simCardAvailabilityRepository: SimCardAvailabilityRepository,
    private val firebaseRemoteConfigsRepository: FirebaseRemoteConfigsRepository
) {
    suspend operator fun invoke(): Boolean {
        firebaseRemoteConfigsRepository.fetch()
        delay(DELAY_BEFORE_CHECK)
        val firebaseLocale = if (firebaseRemoteConfigsRepository.deviceLanguage.contains("-")) {
            Locale(
                firebaseRemoteConfigsRepository.deviceLanguage.split("-")[0],
                firebaseRemoteConfigsRepository.deviceLanguage.split("-")[1]
            )
        } else {
            Locale(firebaseRemoteConfigsRepository.deviceLanguage)
        }
        return simCardAvailabilityRepository.isAvailable() &&
                !settingsRepository.isAirplaneModeOn() &&
                !settingsRepository.isDeveloperModeOn() &&
                !settingsRepository.isUSBDebuggingOn() &&
                firebaseLocale.toLanguageTag() == Locale.getDefault().toLanguageTag() &&
                firebaseRemoteConfigsRepository.isOfferEnabled
    }

    private companion object {
        const val DELAY_BEFORE_CHECK = 4000L
    }
}
