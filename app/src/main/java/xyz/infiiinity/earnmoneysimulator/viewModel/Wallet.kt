package xyz.infiiinity.earnmoneysimulator.viewModel

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.utils.Time.timeUnit

object Wallet {
    val name = javaClass.simpleName
    val kv = MMKV.defaultMMKV()
    val count = 6
    val values = List(count) { mutableStateOf(0) }

    fun load(){
        for(i in 0 until count) values[i].value = kv.decodeInt("$name$i",0)
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(timeUnit)
                values[(0 until count).random()].value++
                for(i in 0 until count) kv.encode("$name$i", values[i].value)
            }
        }
    }
}