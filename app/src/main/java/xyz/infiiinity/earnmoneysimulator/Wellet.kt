package xyz.infiiinity.earnmoneysimulator

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Wellet {

    val crypto = mutableStateOf(0)
    val fossil = mutableStateOf(0)
    val preciousMetal = mutableStateOf(0)
    val basicMetal = mutableStateOf(0)
    val lightMetal = mutableStateOf(0)

    fun getCrypto(){
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(100)
                crypto.value++
            }
        }
    }

    fun startMine() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(100)
                when ((0..3).random()) {
                    0 -> fossil.value++
                    1 -> preciousMetal.value++
                    2 -> basicMetal.value++
                    3 -> lightMetal.value++
                }
            }
        }
    }
}