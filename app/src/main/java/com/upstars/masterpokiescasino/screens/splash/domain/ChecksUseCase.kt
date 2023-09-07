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
    suspend operator fun invoke(): Boolean {
        firebaseRemoteConfigsRepository.fetch()
        val allowedLanguages = firebaseRemoteConfigsRepository.deviceLanguage.split("/")
            .map {
                if (it.contains("-")) {
                    val (lang, country) = it.split("-")
                    Locale(lang, country)
                } else {
                    Locale(it)
                }
            }.map(Locale::toLanguageTag)
        return simCardAvailabilityRepository.isAvailable() &&
                !settingsRepository.isAirplaneModeOn() &&
                !settingsRepository.isDeveloperModeOn() &&
                !settingsRepository.isUSBDebuggingOn() &&
                allowedLanguages.contains(Locale.getDefault().toLanguageTag()) &&
                firebaseRemoteConfigsRepository.isOfferEnabled
    }
}
