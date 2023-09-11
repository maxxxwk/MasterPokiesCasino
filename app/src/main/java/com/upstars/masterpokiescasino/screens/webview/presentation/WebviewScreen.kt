package com.upstars.masterpokiescasino.screens.webview.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup.LayoutParams
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Suppress("FunctionNaming")
@Composable
fun WebviewScreen(viewModel: WebviewScreenViewModel) {
    var goBack by remember { mutableStateOf(Runnable {}) }
    var canGoBack by remember { mutableStateOf(false) }

    BackHandler(enabled = canGoBack, onBack = goBack::run)

    var filePathReceivedCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri -> uri?.let { filePathReceivedCallback?.onReceiveValue(arrayOf(it)) } }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    cacheMode = WebSettings.LOAD_DEFAULT
                    allowFileAccess = true
                }
                with(CookieManager.getInstance()) {
                    setAcceptCookie(true)
                    setAcceptThirdPartyCookies(this@apply, true)
                }
                webViewClient = object : WebViewClient() {
                    override fun doUpdateVisitedHistory(
                        view: WebView,
                        url: String?,
                        isReload: Boolean
                    ) {
                        super.doUpdateVisitedHistory(view, url, isReload)
                        canGoBack = view.canGoBack()
                    }
                }
                webChromeClient = object : WebChromeClient() {
                    override fun onShowFileChooser(
                        webView: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        filePathReceivedCallback = filePathCallback
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
                goBack = Runnable { this.goBack() }
                loadUrl(viewModel.getURL())
            }
        }
    )
}
