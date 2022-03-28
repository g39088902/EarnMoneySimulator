package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.Plan.MINING
import xyz.infiiinity.earnmoneysimulator.model.Plan.WORK
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv

object Wallet : BaseModel(R.array.wallet) {

    const val CRYPTO = 0
    const val ELECTRICITY = 1
    const val FOSSIL = 2
    const val PRECIOUS_METAL = 3
    const val BASIC_METAL = 4

    override fun doEachSecond() {
        if(Plan.select.value==MINING)values[(2..lastIndex).random()].value++
        super.doEachSecond()
    }

    fun doEachMinute() {
        if(Plan.select.value==WORK)values[CRYPTO].value += Skill.values.maxOf { it.value }
        kv.encode("$name$CRYPTO", values[CRYPTO].value)
    }

}