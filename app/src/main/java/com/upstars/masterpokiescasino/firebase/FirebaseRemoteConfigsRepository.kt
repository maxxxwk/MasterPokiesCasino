package com.upstars.masterpokiescasino.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ktx.configUpdates
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.upstars.masterpokiescasino.di.DispatcherIO
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Reusable
class FirebaseRemoteConfigsRepository @Inject constructor(
    @DispatcherIO private val dispatcher: CoroutineDispatcher
) {
    val deviceLanguage: String get() = Firebase.remoteConfig.getString("device_language")
    val offerURL: String get() = Firebase.remoteConfig.getString("offer_url")
    val isOfferEnabled: Boolean get() = Firebase.remoteConfig.getBoolean("is_offer_enabled")
    val privacyPolicy: String get() = Firebase.remoteConfig.getString("privacy_policy")

    suspend fun fetch(): Unit = withContext(dispatcher) {
        Firebase.remoteConfig.fetchAndActivate().await()
        merge<ConfigUpdate?>(
            flow {
                delay(TIME_FOR_WAITING_UPDATES)
                emit(null)
            },
            Firebase.remoteConfig.configUpdates
        ).first()?.let { Firebase.remoteConfig.activate().await() }
    }

    private companion object {
        const val TIME_FOR_WAITING_UPDATES = 4000L
    }
}
