package com.upstars.masterpokiescasino.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FirebaseRemoteConfigsRepository @Inject constructor() {
    val deviceLanguage: String get() = Firebase.remoteConfig.getString("device_language")
    val offerURL: String get() = Firebase.remoteConfig.getString("offer_url")
    val isOfferEnabled: Boolean get() = Firebase.remoteConfig.getBoolean("is_offer_enabled")
    val privacyPolicy: String get() = Firebase.remoteConfig.getString("privacy_policy")
}
