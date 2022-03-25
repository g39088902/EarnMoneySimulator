package xyz.infiiinity.earnmoneysimulator.model

import androidx.compose.runtime.mutableStateOf
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv
import xyz.infiiinity.earnmoneysimulator.utils.Resource.ResourceLastIndex

object Plan {
    val name = javaClass.simpleName
    val lastIndex = ResourceLastIndex(R.array.plan)
    val count = lastIndex + 1
    val select = mutableStateOf(-1)

    fun load() {
        select.value = kv.decodeInt(name, -1)
    }

    fun doEachSecond() {
        kv.encode(name, select.value)
    }

    const val STUDY = 0
    const val MINING = 1
}