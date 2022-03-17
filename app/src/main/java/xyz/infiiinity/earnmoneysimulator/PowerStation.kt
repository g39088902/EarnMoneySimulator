package xyz.infiiinity.earnmoneysimulator

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context
import xyz.infiiinity.earnmoneysimulator.Time.timeUnit
import xyz.infiiinity.earnmoneysimulator.Tips.toast
import java.lang.Integer.max
import java.lang.Integer.min

object PowerStation {

    val kv: MMKV = MMKV.defaultMMKV()
    val powerStation = mutableStateOf(0)

    fun load(){
        powerStation.value = kv.decodeInt("powerStation",0)
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                val generateAbility = 1 * powerStation.value
                val generateActual = min(generateAbility, max(Wallet.values[2].value, 0))
                Wallet.values[2].value -= generateActual
                Wallet.values[3].value += generateActual
                kv.encode("powerStation",powerStation.value)
                delay(timeUnit*5)
            }
        }
    }

    fun buildPowerStation(){
        Wallet.values[4].value -= 100
        powerStation.value++
    }

    fun destoryPowerStation(){
        if (powerStation.value == 0) toast(context.resources.getString(R.string.no_item)) else powerStation.value--
    }
}