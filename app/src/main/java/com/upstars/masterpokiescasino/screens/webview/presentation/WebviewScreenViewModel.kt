package com.upstars.masterpokiescasino.screens.webview.presentation

import androidx.lifecycle.ViewModel
import com.upstars.masterpokiescasino.screens.webview.domain.GetURLUseCase
import javax.inject.Inject

class WebviewScreenViewModel @Inject constructor(
    private val getURLUseCase: GetURLUseCase
) : ViewModel() {
    fun getURL() = getURLUseCase()
}
