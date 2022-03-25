package xyz.infiiinity.earnmoneysimulator.model

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.Plan.MINING
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv
import xyz.infiiinity.earnmoneysimulator.utils.Resource.ResourceLastIndex

object Wallet {
    val name = javaClass.simpleName

    val lastIndex = ResourceLastIndex(R.array.wallet)
    val count = lastIndex + 1
    val values = List(count) { mutableStateOf(0) }

    fun load() {
        for (i in 0..lastIndex) values[i].value = kv.decodeInt("$name$i", 0)
    }

    fun doEachSecond() {
        if(Plan.select.value==MINING)values[(0..lastIndex).random()].value++
        for (i in 0..lastIndex) kv.encode("$name$i", values[i].value)
    }

    const val CRYPTO = 0
    const val ELECTRICITY = 1
    const val FOSSIL = 2
    const val PRECIOUS_METAL = 3
    const val BASIC_METAL = 4
}