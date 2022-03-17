package xyz.infiiinity.earnmoneysimulator.utils

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object Http {
    val okHttpClient = OkHttpClient.Builder()
        .pingInterval(10, TimeUnit.SECONDS)
        .build()
}