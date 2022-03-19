package xyz.infiiinity.earnmoneysimulator.viewModel

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.utils.Time.timeUnit
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast
import java.lang.Integer.max
import java.lang.Integer.min

object PowerStation {
    val name = javaClass.simpleName
    val kv: MMKV = MMKV.defaultMMKV()
    val powerStation = mutableStateOf(0)

    fun load(){
        powerStation.value = kv.decodeInt(name,0)
    }

    suspend fun doEach5Second(){
        val generateAbility = 1 * powerStation.value
        val generateActual = min(generateAbility, max(Wallet.values[2].value, 0))
        Wallet.values[2].value -= generateActual
        Wallet.values[3].value += generateActual
        kv.encode(name, powerStation.value)
    }

    fun buildPowerStation(){
        Wallet.values[4].value -= 100
        powerStation.value++
    }

    fun destoryPowerStation(){
        if (powerStation.value == 0) toast(context.resources.getString(R.string.no_item)) else powerStation.value--
    }
}