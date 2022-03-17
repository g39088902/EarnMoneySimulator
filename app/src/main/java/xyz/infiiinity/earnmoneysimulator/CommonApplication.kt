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
        fun stringRes(id:Int) = context.resources.getString(id)
        fun stringRes(id:Int,index:Int) = context.resources.getStringArray(id)[index]
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