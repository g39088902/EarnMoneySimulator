package xyz.infiiinity.earnmoneysimulator

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import kotlin.properties.Delegates

class CommonApplication: Application(){
    companion object {
        private val TAG = "CommonApplication"
        var context: Context by Delegates.notNull()
        private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MMKV.initialize(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}