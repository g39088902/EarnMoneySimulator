package xyz.infiiinity.earnmoneysimulator

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.model.*
import kotlin.properties.Delegates

class CommonApplication: Application(){
    companion object {
        var context: Context by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MMKV.initialize(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Wallet.load()
        RealEstate.load()
        Plan.load()
        Skill.load()
        PowerStation.load()
        Agriculture.load()
    }
}