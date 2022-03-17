package xyz.infiiinity.earnmoneysimulator

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.stringRes
import xyz.infiiinity.earnmoneysimulator.Time.timeUnit

object Skill {

    val kv = MMKV.defaultMMKV()
    val count = 6
    val values = List(count) { mutableStateOf(0) }

    fun loadSkill(){
        for(i in 0 until count) values[i].value = kv.decodeInt(stringRes(R.array.skill,i),0)
    }

    fun saveSkill(){
        for(i in 0 until count) kv.encode(stringRes(R.array.skill,i), values[i].value)
    }

    fun startStudy() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(timeUnit)
                values[(0 until count).random()].value++
                saveSkill()
            }
        }
    }
}