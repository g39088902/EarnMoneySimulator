package xyz.infiiinity.earnmoneysimulator.utils

import kotlinx.coroutines.*
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.model.*

object Time {
    val timeUnit = 150L
    const val MINUTE = 10
    const val HOUR = 10 * MINUTE

    val timeHook = CoroutineScope(Dispatchers.Default).launch(start = CoroutineStart.LAZY) {
        var i = 0
        while (isActive) {
            delay(timeUnit)
            Wallet.doEachSecond()
            RealEstate.doEachSecond()
            Plan.doEachSecond()
            Skill.doEachSecond()
            Agriculture.doEachSecond()
            if (i % MINUTE == 1) {
                Wallet.doEachMinute()
                PowerStation.doEachMinute()
            }
            if (i % HOUR == 2) {
                WaifuSocket.nextWaifu()
            }
            i++
        }
    }
}