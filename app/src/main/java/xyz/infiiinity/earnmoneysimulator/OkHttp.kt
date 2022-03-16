package xyz.infiiinity.earnmoneysimulator

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Base64
import android.util.Log
import kotlinx.coroutines.*
import okhttp3.*
import xyz.infiiinity.earnmoneysimulator.Fson.getImages
import java.util.*
import java.util.concurrent.TimeUnit

object OkHttp {

    private val okHttpClient = OkHttpClient.Builder()
        .pingInterval(10, TimeUnit.SECONDS)
        .build()
    val waifuDeque = ArrayDeque<String>(0)
    val waifuBitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888)
    val waifuLabsUrl = "wss://waifulabs.com/creator/socket/websocket?token=SFMyNTY.g2gDZAACb2tuBgBCYHKSfwFiAAFRgA.VdkAIk-8pj8QwqMZ1Az1Z0-EkOgBKhLuGBqK8xRrOQM&vsn=2.0.0"
    val waifuRequest = Request.Builder()
        .url(waifuLabsUrl)
        .build()
    val waifuListener = object : WebSocketListener(){
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Log.i("Waifu",response.message)
        }
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            Log.i("Waifu",text)
            val picList = getImages(text)
            waifuDeque.addAll(picList)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Log.i("Waifu",response?.message ?: "")
        }
    }
    val waifuSocket = okHttpClient.newWebSocket(waifuRequest, waifuListener)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            CoroutineScope(Dispatchers.Default).launch {
                while (true) {
                    delay(Time.timeUnit)
                    nextWaifu()
                }
            }
        }
    }

    fun startWaifuSocket(){
        waifuSocket.send("[\"1\",\"1\",\"api\",\"phx_join\",{}]")
        waifuSocket.send("[\"1\",\"1\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
    }

    fun nextWaifu(){
        if(!waifuDeque.isEmpty()) {
            val bitmapArray = Base64.decode(waifuDeque.removeFirst(), Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
            Canvas(waifuBitmap).drawBitmap(bitmap, 0f, 0f, null)
        }
        if(waifuDeque.size<8) waifuSocket.send("[\"1\",\"1\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
    }

}