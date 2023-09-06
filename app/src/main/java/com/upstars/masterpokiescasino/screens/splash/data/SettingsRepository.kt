package com.upstars.masterpokiescasino.screens.splash.data

import android.content.Context
import android.provider.Settings
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val context: Context) {

    fun isAirplaneModeOn(): Boolean = Settings.System.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON,
        0
    ) != 0

    fun isDeveloperModeOn(): Boolean = Settings.System.getInt(
        context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) != 0

    fun isUSBDebuggingOn(): Boolean = Settings.System.getInt(
        context.contentResolver,
        Settings.Global.ADB_ENABLED,
        0
    ) != 0
}
