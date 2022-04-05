package xyz.infiiinity.earnmoneysimulator.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.*
import okhttp3.*
import xyz.infiiinity.earnmoneysimulator.utils.Fson.getImagesFromJson
import xyz.infiiinity.earnmoneysimulator.utils.Fson.getTokenFromHtml
import xyz.infiiinity.earnmoneysimulator.utils.Http.okHttpClient
import xyz.infiiinity.earnmoneysimulator.utils.MMKV.kv

object WaifuSocket {

    val waifuList = mutableStateListOf<String>()
    val listener = object : WebSocketListener(){
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            CoroutineScope(Dispatchers.Default).launch {
                waifuList.addAll(getImagesFromJson(text))
            }
        }
    }
    lateinit var socket:WebSocket

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val tokenUrl = "https://waifulabs.com/generate"
            val tokenRequest = Request.Builder().url(tokenUrl).build()
            val tokenResponse = okHttpClient.newCall(tokenRequest).execute().body
            val token = getTokenFromHtml(tokenResponse?.string() ?: "")
            val generateUrl = "wss://waifulabs.com/creator/socket/websocket?token=$token&vsn=2.0.0"
            val generateRequest = Request.Builder().url(generateUrl).build()
            socket = okHttpClient.newWebSocket(generateRequest, listener)
        }
    }

    fun base64ToBitmap(string: String):Bitmap{
        val picArray = Base64.decode(string, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(picArray, 0, picArray.size)
    }

    fun nextWaifu(){
        socket.send("[\"1\",\"1\",\"api\",\"phx_join\",{}]")
        socket.send("[\"1\",\"1\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
    }

    fun load(){
        val waifuSet = kv.decodeStringSet("waifu") ?: setOf()
        waifuList.addAll(waifuSet.toList())
    }

    fun doEachMinute(){
        kv.encode("waifu", waifuList.toSet())
    }
}