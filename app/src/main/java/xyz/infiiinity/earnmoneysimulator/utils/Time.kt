package xyz.infiiinity.earnmoneysimulator.utils

import kotlinx.coroutines.*
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.model.Plan
import xyz.infiiinity.earnmoneysimulator.model.PowerStation
import xyz.infiiinity.earnmoneysimulator.model.Skill
import xyz.infiiinity.earnmoneysimulator.model.Wallet

object Time {
    val timeUnit = 150L
    val timeHook = CoroutineScope(Dispatchers.Default).launch(start = CoroutineStart.LAZY) {
        var i=0
        while (isActive){
            delay(timeUnit)
            Wallet.doEachSecond()
            Skill.doEachSecond()
            Plan.doEachSecond()
            if (i%5==1)PowerStation.doEach5Second()
            if (i%10==2) WaifuSocket.nextWaifu()
            i++
        }
    }
}