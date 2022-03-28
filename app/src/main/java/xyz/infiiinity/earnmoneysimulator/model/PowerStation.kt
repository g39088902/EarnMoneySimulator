package xyz.infiiinity.earnmoneysimulator.model

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context
import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.Wallet.BASIC_METAL
import xyz.infiiinity.earnmoneysimulator.model.Wallet.ELECTRICITY
import xyz.infiiinity.earnmoneysimulator.model.Wallet.FOSSIL
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast
import java.lang.Integer.max
import java.lang.Integer.min

object PowerStation {
    val name = javaClass.simpleName
    val powerStation = mutableStateOf(0)

    fun load(){
        powerStation.value = kv.decodeInt(name,0)
    }

    fun doEachMinute(){
        val generateAbility = 1 * powerStation.value
        val generateActual = min(generateAbility, max(Wallet.values[2].value, 0))
        Wallet.values[FOSSIL].value -= generateActual
        Wallet.values[ELECTRICITY].value += generateActual
        kv.encode(name, powerStation.value)
    }

    fun buildPowerStation(){
        Wallet.values[BASIC_METAL].value -= 100
        powerStation.value++
    }

    fun destoryPowerStation(){
        if (powerStation.value == 0) toast(context.resources.getString(R.string.no_item)) else powerStation.value--
    }
}