package xyz.infiiinity.earnmoneysimulator.utils

import androidx.compose.runtime.mutableStateOf
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

    val gameSecond = mutableStateOf(0)
    val gameMinute = mutableStateOf(0)
    val gameHour = mutableStateOf(0)
    val gameDay = mutableStateOf(0)
    val gameMouth = mutableStateOf(0)
    val gameYear = mutableStateOf(0)

    override fun doEachSecond() {
        values[GAME_TIME].value++
        gameSecond.value = values[GAME_TIME].value % MINUTE
        super.doEachSecond()
    }

    override fun load() {
        super.load()
        doEachMinute()
        doEachHour()
        doEachDay()
        doEachMouth()
        doEachYear()
    }

    fun doEachMinute() {
        gameMinute.value = values[GAME_TIME].value % HOUR / MINUTE
    }

    fun doEachHour() {
        gameHour.value = values[GAME_TIME].value % DAY / HOUR
        with(values[CHARTER_AGE]) {
            val timeToDie = (0..value).random() > 66
            if (timeToDie) {
                Skill.clear()
                value = 0
                toast("你死了，你的孩子继承了你的财产")
            } else value++
        }
    }

    fun doEachDay() {
        gameDay.value = values[GAME_TIME].value % MOUTH / DAY
    }

    fun doEachMouth() {
        gameMouth.value = values[GAME_TIME].value % YEAR / MOUTH
    }

    fun doEachYear() {
        gameDay.value = values[GAME_TIME].value / YEAR
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
            if (values[GAME_TIME].value % MINUTE == 0) {
                doEachMinute()
                PowerStation.doEachMinute()
                Shop.doEachMinute()
                WaifuSocket.doEachMinute()
            }
            if (values[GAME_TIME].value % HOUR == 0) {
                doEachHour()
            }
            if (values[GAME_TIME].value % DAY == 0) {
                doEachDay()
            }
            if (values[GAME_TIME].value % MOUTH == 0) {
                doEachMouth()
            }
            if (values[GAME_TIME].value % YEAR == 0) {
                doEachYear()
            }
        }
    }
}