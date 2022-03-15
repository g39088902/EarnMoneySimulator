package xyz.infiiinity.earnmoneysimulator

import androidx.compose.runtime.mutableStateOf
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Wallet {

    lateinit var kv:MMKV

    val crypto = mutableStateOf(0)
    val electricity = mutableStateOf(0)
    val fossil = mutableStateOf(0)
    val preciousMetal = mutableStateOf(0)
    val basicMetal = mutableStateOf(0)
    val lightMetal = mutableStateOf(0)

    fun loadMMKV(){
        kv = MMKV.defaultMMKV()
        crypto.value = kv.decodeInt("crypto")
        electricity.value = kv.decodeInt("electricity")
        fossil.value = kv.decodeInt("fossil")
        preciousMetal.value = kv.decodeInt("preciousMetal")
        basicMetal.value = kv.decodeInt("basicMetal")
        lightMetal.value = kv.decodeInt("lightMetal")
    }

    fun saveMMKV(){
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
                delay(1000)
                when ((0..3).random()) {
                    0 -> fossil.value++
                    1 -> preciousMetal.value++
                    2 -> basicMetal.value++
                    3 -> lightMetal.value++
                }
                saveMMKV()
            }
        }
    }

    fun powerGenerate(){
        fossil.value -= 100
        electricity.value += 100
    }
}