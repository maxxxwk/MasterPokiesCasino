package com.upstars.masterpokiescasino.navigation

const val COUNT_ARGUMENT_KEY = "COUNT"
const val PRIZE_TYPE_ARGUMENT_KEY = "PRIZE_TYPE"

enum class NavigationRoute(val route: String) {
    SPLASH("SPLASH"),
    MAIN("MAIN"),
    PRIVACY_POLICY("PRIVACY_POLICY"),
    SLOTS("SLOTS"),
    ROULETTE_WHEEL("ROULETTE_WHEEL"),
    WEBVIEW("WEBVIEW"),
    RESULT("RESULT/{${COUNT_ARGUMENT_KEY}}/{$PRIZE_TYPE_ARGUMENT_KEY}");
}
