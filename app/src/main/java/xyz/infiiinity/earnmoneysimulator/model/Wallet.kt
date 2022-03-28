package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.Plan.MINING
import xyz.infiiinity.earnmoneysimulator.model.Plan.WORK
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv

object Wallet : BaseModel(R.array.wallet) {

    const val CRYPTO = 0

    fun doEachMinute() {
        if(Plan.select.value==WORK)values[CRYPTO].value += Skill.values.maxOf { it.value }
        kv.encode("$name$CRYPTO", values[CRYPTO].value)
    }

}