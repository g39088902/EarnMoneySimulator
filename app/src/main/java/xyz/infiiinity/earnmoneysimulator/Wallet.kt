package xyz.infiiinity.earnmoneysimulator

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.Time.timeUnit

object Wallet {

    val kv = MMKV.defaultMMKV()
    val crypto = mutableStateOf(0)
    val electricity = mutableStateOf(0)
    val fossil = mutableStateOf(0)
    val preciousMetal = mutableStateOf(0)
    val basicMetal = mutableStateOf(0)
    val lightMetal = mutableStateOf(0)

    fun loadWallet(){
        crypto.value = kv.decodeInt("crypto",0)
        electricity.value = kv.decodeInt("electricity",0)
        fossil.value = kv.decodeInt("fossil",0)
        preciousMetal.value = kv.decodeInt("preciousMetal",0)
        basicMetal.value = kv.decodeInt("basicMetal",0)
        lightMetal.value = kv.decodeInt("lightMetal",0)
    }

    fun saveWallet(){
        kv.encode("crypto",crypto.value)
        kv.encode("electricity",electricity.value)
        kv.encode("fossil",fossil.value)
        kv.encode("preciousMetal",preciousMetal.value)
        kv.encode("basicMetal",basicMetal.value)
        kv.encode("lightMetal",lightMetal.value)
    }

    fun startMine() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(timeUnit)
                when ((0..3).random()) {
                    0 -> fossil.value++
                    1 -> preciousMetal.value++
                    2 -> basicMetal.value++
                    3 -> lightMetal.value++
                }
                saveWallet()
            }
        }
    }
}