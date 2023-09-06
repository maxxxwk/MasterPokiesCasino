package com.upstars.masterpokiescasino.screens.slots.presentation

@Suppress("MagicNumber")
fun getCoinsPrize(
    result: Triple<SlotMachineItem, SlotMachineItem, SlotMachineItem>
): Int = when (result) {
    Triple(SlotMachineItem.BELL, SlotMachineItem.BELL, SlotMachineItem.BELL) -> 200

    Triple(SlotMachineItem.KEY, SlotMachineItem.KEY, SlotMachineItem.KEY) -> 300

    Triple(SlotMachineItem.CROWN, SlotMachineItem.CROWN, SlotMachineItem.CROWN) -> 500

    Triple(SlotMachineItem.BELL, SlotMachineItem.BELL, SlotMachineItem.KEY),
    Triple(SlotMachineItem.BELL, SlotMachineItem.BELL, SlotMachineItem.CROWN),
    Triple(SlotMachineItem.KEY, SlotMachineItem.BELL, SlotMachineItem.BELL),
    Triple(SlotMachineItem.CROWN, SlotMachineItem.BELL, SlotMachineItem.BELL) -> 50

    Triple(SlotMachineItem.KEY, SlotMachineItem.KEY, SlotMachineItem.BELL),
    Triple(SlotMachineItem.KEY, SlotMachineItem.KEY, SlotMachineItem.CROWN),
    Triple(SlotMachineItem.BELL, SlotMachineItem.KEY, SlotMachineItem.KEY),
    Triple(SlotMachineItem.CROWN, SlotMachineItem.KEY, SlotMachineItem.KEY) -> 100

    Triple(SlotMachineItem.CROWN, SlotMachineItem.CROWN, SlotMachineItem.BELL),
    Triple(SlotMachineItem.CROWN, SlotMachineItem.CROWN, SlotMachineItem.KEY),
    Triple(SlotMachineItem.BELL, SlotMachineItem.CROWN, SlotMachineItem.CROWN),
    Triple(SlotMachineItem.KEY, SlotMachineItem.CROWN, SlotMachineItem.CROWN) -> 150

    else -> 10
}