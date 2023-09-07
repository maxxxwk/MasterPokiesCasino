package com.upstars.masterpokiescasino.screens.webview.presentation

import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.webkit.WebSettings
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Suppress("FunctionNaming")
@Composable
fun WebviewScreen(viewModel: WebviewScreenViewModel) {
    WebView(
        modifier = Modifier.fillMaxSize(),
        state = rememberWebViewState(url = viewModel.getURL()),
        onCreated = { webview ->
            webview.settings.javaScriptEnabled = true
            webview.settings.domStorageEnabled = true
            webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
            CookieManager.getInstance().setAcceptCookie(true)
            CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true)
        }
    )
}
