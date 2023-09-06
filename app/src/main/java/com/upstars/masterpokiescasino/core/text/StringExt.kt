package com.upstars.masterpokiescasino.core.text

fun String.normalizeDigitsInCasinoFlatText() = this.map {
    when (it) {
        '4' -> '5'
        '5' -> '4'
        else -> it
    }
}.joinToString(separator = "")
