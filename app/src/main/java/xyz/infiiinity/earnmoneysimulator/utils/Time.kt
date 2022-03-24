package xyz.infiiinity.earnmoneysimulator.utils

import kotlinx.coroutines.*
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.viewModel.PowerStation
import xyz.infiiinity.earnmoneysimulator.viewModel.Skill
import xyz.infiiinity.earnmoneysimulator.viewModel.Wallet

object Time {
    val timeUnit = 1500L
    val timeHook = CoroutineScope(Dispatchers.Default).launch(start = CoroutineStart.LAZY) {
        var i=0
        while (isActive){
            delay(timeUnit)
            Wallet.doEachSecond()
            Skill.doEachSecond()
            if (i%5==1)PowerStation.doEach5Second()
            if (i%10==2) WaifuSocket.nextWaifu()
            i++
        }
    }
}