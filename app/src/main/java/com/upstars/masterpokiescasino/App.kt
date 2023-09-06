package com.upstars.masterpokiescasino

import android.app.Application
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.upstars.masterpokiescasino.di.AppComponent
import com.upstars.masterpokiescasino.di.DaggerAppComponent

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = REMOTE_CONFIGS_MIN_FETCH_INTERVAL
                }
            )
            setDefaultsAsync(R.xml.remote_config_defaults)
            addOnConfigUpdateListener(
                object : ConfigUpdateListener {
                    override fun onUpdate(configUpdate: ConfigUpdate) {
                        fetchAndActivate()
                    }

                    override fun onError(error: FirebaseRemoteConfigException) {
                        Log.e("App", "Configs update error", error)
                    }
                }
            )
        }
    }

    private companion object {
        const val REMOTE_CONFIGS_MIN_FETCH_INTERVAL = 3600L
    }
}
