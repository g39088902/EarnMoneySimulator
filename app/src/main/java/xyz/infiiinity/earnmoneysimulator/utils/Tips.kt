package xyz.infiiinity.earnmoneysimulator.utils

import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context

object Tips {
    var lastDisplayString = ""
    var lastDisplayTime = 0L

    fun toast(str: String) { CoroutineScope(Dispatchers.Main).launch {
            if (str == lastDisplayString && System.currentTimeMillis() < lastDisplayTime) return@launch
            lastDisplayString = str
            lastDisplayTime = System.currentTimeMillis() + 4000
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    } }
}