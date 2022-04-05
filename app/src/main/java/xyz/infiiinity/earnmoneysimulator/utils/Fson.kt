package xyz.infiiinity.earnmoneysimulator.utils

import android.util.Log

object Fson {
    fun getImagesFromJson(payload:String):List<String>{
        val regex = Regex("(?<=\"image\":\").*?(?=\",\"seeds\")")
        val regexResult = regex.findAll(payload)
        val result = mutableListOf<String>()
        for(item in regexResult) {
            result.add(item.value)
        }
        Log.i("Waifu","Get ${result.size} New Waifu")
        return result
    }

    fun getTokenFromHtml(payload: String):String{
        val regex = Regex("(?<=window.authToken = \").*?(?=\")")
        return regex.find(payload)?.value ?: ""
    }
}