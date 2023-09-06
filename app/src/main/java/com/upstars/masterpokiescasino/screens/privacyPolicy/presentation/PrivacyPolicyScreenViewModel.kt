package com.upstars.masterpokiescasino.screens.privacyPolicy.presentation

import androidx.lifecycle.ViewModel
import com.upstars.masterpokiescasino.screens.privacyPolicy.domain.GetPrivacyPolicyUseCase
import javax.inject.Inject

class PrivacyPolicyScreenViewModel @Inject constructor(
    private val getPrivacyPolicyUseCase: GetPrivacyPolicyUseCase
) : ViewModel() {
    fun getPrivacyPolicy(): String = getPrivacyPolicyUseCase()
}
