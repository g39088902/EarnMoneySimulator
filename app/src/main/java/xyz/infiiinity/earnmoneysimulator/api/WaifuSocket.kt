package xyz.infiiinity.earnmoneysimulator.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.util.Base64
import kotlinx.coroutines.*
import okhttp3.*
import xyz.infiiinity.earnmoneysimulator.utils.Fson.getImagesFromJson
import xyz.infiiinity.earnmoneysimulator.utils.Fson.getTokenFromHtml
import xyz.infiiinity.earnmoneysimulator.utils.Http.okHttpClient
import java.util.*

object WaifuSocket {

    val deque = ArrayDeque<String>(0)
    val bitmap = Bitmap.createBitmap(256,256,Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val listener = object : WebSocketListener(){
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            CoroutineScope(Dispatchers.Default).launch {
                val picList = getImagesFromJson(text)
                deque.addAll(picList)
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
            socket.send("[\"1\",\"1\",\"api\",\"phx_join\",{}]")
            socket.send("[\"1\",\"1\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
        }
    }

    fun nextWaifu(){
        if(!deque.isEmpty()) {
            val bitmapArray = Base64.decode(deque.removeFirst(), Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
            canvas.drawColor(Color.WHITE)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            if(deque.size<8) socket.send("[\"1\",\"1\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
        }
    }

}