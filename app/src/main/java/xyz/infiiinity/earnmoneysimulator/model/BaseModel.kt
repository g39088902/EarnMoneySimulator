package xyz.infiiinity.earnmoneysimulator.model

import androidx.compose.runtime.mutableStateOf
import xyz.infiiinity.earnmoneysimulator.utils.MMKV
import xyz.infiiinity.earnmoneysimulator.utils.Resource

open class BaseModel(val resId:Int) {
    val name = javaClass.simpleName
    val lastIndex = Resource.ResourceLastIndex(resId)
    val count = lastIndex + 1
    val values = List(count) { mutableStateOf(0) }

    open fun load() {
        for (i in 0..lastIndex) values[i].value = MMKV.kv.decodeInt("$name$i", 0)
    }

    open fun doEachSecond() {
        for (i in 0..lastIndex) MMKV.kv.encode("$name$i", values[i].value)
    }
}