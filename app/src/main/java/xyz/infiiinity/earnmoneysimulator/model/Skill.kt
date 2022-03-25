package xyz.infiiinity.earnmoneysimulator.model

import androidx.compose.runtime.mutableStateOf
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv
import xyz.infiiinity.earnmoneysimulator.utils.Resource.ResourceLastIndex

object Skill {
    val name = javaClass.simpleName
    val lastIndex = ResourceLastIndex(R.array.skill)
    val count = lastIndex + 1
    val values = List(count) { mutableStateOf(0) }

    fun load() {
        for (i in 0..lastIndex) values[i].value = kv.decodeInt("$name$i", 0)
    }

    fun doEachSecond() {
        if(Plan.select.value== Plan.STUDY)values[(0..lastIndex).random()].value++
        for (i in 0..lastIndex) kv.encode("$name$i", values[i].value)
    }
}