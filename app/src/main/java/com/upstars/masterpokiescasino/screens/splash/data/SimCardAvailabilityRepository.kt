package com.upstars.masterpokiescasino.screens.splash.data

import android.content.Context
import android.telephony.TelephonyManager
import javax.inject.Inject

class SimCardAvailabilityRepository @Inject constructor(context: Context) {

    private val telephonyManager: TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    fun isAvailable(): Boolean =
        telephonyManager.simState != TelephonyManager.SIM_STATE_ABSENT
}
