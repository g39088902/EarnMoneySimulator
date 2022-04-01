package xyz.infiiinity.earnmoneysimulator.utils

import kotlinx.coroutines.*
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.model.*
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

object Time : BaseModel(R.array.time) {
    val timeUnit = 50L
    const val GAME_TIME = 0
    const val CHARTER_AGE = 1
    const val MINUTE = 10
    const val HOUR = 10 * MINUTE
    const val DAY = 10 * HOUR
    const val MOUTH = 10 * DAY
    const val YEAR = 10 * MOUTH

    override fun doEachSecond() {
        values[GAME_TIME].value++
        super.doEachSecond()
    }

    fun doEachHour() {
        with(values[CHARTER_AGE]) {
            val timeToDie = (0..value).random() > 50
            if (timeToDie) {
                Skill.clear()
                value = 0
                toast("你死了，你的孩子继承了你的财产")
            } else value++
        }
        super.doEachSecond()
    }

    val timeHook = CoroutineScope(Dispatchers.Default).launch(start = CoroutineStart.LAZY) {
        while (isActive) {
            delay(timeUnit)
            doEachSecond()
            Wallet.doEachSecond()
            Plan.doEachSecond()
            Skill.doEachSecond()
            RealEstate.doEachSecond()
            for (factory in RealEstate.factoryList) factory.doEachSecond()
            if (values[GAME_TIME].value % MINUTE == MINUTE - 1) {
                Wallet.doEachMinute()
                PowerStation.doEachMinute()
                Shop.doEachMinute()
            }
            if (values[GAME_TIME].value % HOUR == HOUR - 2) {
                doEachHour()
                WaifuSocket.nextWaifu()
            }
        }
    }
}