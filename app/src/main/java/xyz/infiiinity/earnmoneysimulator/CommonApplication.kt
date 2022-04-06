package xyz.infiiinity.earnmoneysimulator

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.infiiinity.earnmoneysimulator.api.WaifuSocket
import xyz.infiiinity.earnmoneysimulator.model.*
import xyz.infiiinity.earnmoneysimulator.utils.Time
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
        CoroutineScope(Dispatchers.Default).launch {
            Time.load()
            Wallet.load()
            Plan.load()
            Skill.load()
            RealEstate.load()
            AgricultureLand.load()
            MiningLand.load()
            PowerStation.load()
            BreadFactory.load()
            WaifuSocket.load()
            Time.timeHook.start()
        }
    }
}