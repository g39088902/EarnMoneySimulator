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
import xyz.infiiinity.earnmoneysimulator.Wallet.basicMetal
import xyz.infiiinity.earnmoneysimulator.Wallet.electricity
import xyz.infiiinity.earnmoneysimulator.Wallet.fossil
import java.lang.Integer.max
import java.lang.Integer.min

object PowerStation {

    val kv: MMKV = MMKV.defaultMMKV()
    val powerStation = mutableStateOf(0)

    fun loadPowerStation(){
        powerStation.value = kv.decodeInt("powerStation",0)
    }

    fun savePowerStation(){
        kv.encode("powerStation",powerStation.value)
    }

    fun powerGenerate(){
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                val generateAbility = 1 * powerStation.value
                val generateActual = min(generateAbility, max(fossil.value, 0))
                fossil.value -= generateActual
                electricity.value += generateActual
                savePowerStation()
                delay(timeUnit)
            }
        }
    }

    fun buildPowerStation(){
        basicMetal.value -= 100
        powerStation.value++
    }

    fun destoryPowerStation(){
        if (powerStation.value == 0) toast(context.resources.getString(R.string.no_item)) else powerStation.value--
    }
}