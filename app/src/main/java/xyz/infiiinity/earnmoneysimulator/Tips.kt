package xyz.infiiinity.earnmoneysimulator

import android.widget.Toast
import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context

object Tips {
    var lastDisplayString = ""
    var lastDisplayTime = 0L

    fun toast(str: String) {
        if (str == lastDisplayString && System.currentTimeMillis() < lastDisplayTime) return
        lastDisplayString = str
        lastDisplayTime = System.currentTimeMillis() + 4000
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
}