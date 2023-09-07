package com.upstars.masterpokiescasino.screens.webview.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Suppress("FunctionNaming")
@Composable
fun WebviewScreen(viewModel: WebviewScreenViewModel) {
    var fileCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        it?.let { fileCallback?.onReceiveValue(arrayOf(it)) }
    }
    WebView(
        modifier = Modifier.fillMaxSize(),
        state = rememberWebViewState(url = viewModel.getURL()),
        onCreated = { webview ->
            webview.settings.javaScriptEnabled = true
            webview.settings.domStorageEnabled = true
            webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
            webview.settings.allowFileAccess = true
            CookieManager.getInstance().setAcceptCookie(true)
            CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true)
        },
        chromeClient = object : AccompanistWebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                fileCallback = filePathCallback
                val acceptTypes = fileChooserParams?.acceptTypes?.filter { it != "" }
                launcher.launch(
                    if (!acceptTypes.isNullOrEmpty()) {
                        acceptTypes.toTypedArray()
                    } else {
                        arrayOf("*/*")
                    }
                )
                return true
            }
        }
    )
}
