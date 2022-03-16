package xyz.infiiinity.earnmoneysimulator

import android.util.Log
import okhttp3.*
import java.util.concurrent.TimeUnit

object OkHttp {

    private val okHttpClient = OkHttpClient.Builder()
        .pingInterval(10, TimeUnit.SECONDS)
        .build()

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
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Log.i("Waifu",response?.message ?: "")
        }
    }

    val waifuSocket = okHttpClient.newWebSocket(waifuRequest, waifuListener)

    fun startWaifuSocket(){
        waifuSocket.send("[\"3\",\"3\",\"api\",\"phx_join\",{}]")
        waifuSocket.send("[\"3\",\"5\",\"api\",\"generate\",{\"id\":1,\"params\":{\"step\":0}}]")
    }

}