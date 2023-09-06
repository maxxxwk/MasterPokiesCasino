@file:Suppress("MagicNumber")

package com.upstars.masterpokiescasino.screens.rouletteWheel.presentation

enum class RouletteWheelPrize(val prize: Int) {
    JACKPOT(500),
    BONUS(300),
    P_100(100),
    P_90(90),
    P_80(80),
    P_70(70),
    P_40(40),
    P_30(30),
    P_20(20),
    P_10(10),
}

val rouletteWheelPrizes = listOf(
    RouletteWheelPrize.JACKPOT,
    RouletteWheelPrize.P_100,
    RouletteWheelPrize.P_70,
    RouletteWheelPrize.BONUS,
    RouletteWheelPrize.P_90,
    RouletteWheelPrize.P_30,
    RouletteWheelPrize.P_80,
    RouletteWheelPrize.P_20,
    RouletteWheelPrize.P_90,
    RouletteWheelPrize.P_10,
    RouletteWheelPrize.P_40,
    RouletteWheelPrize.P_100
)
