package xyz.infiiinity.earnmoneysimulator

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import xyz.infiiinity.earnmoneysimulator.viewModel.PowerStation
import xyz.infiiinity.earnmoneysimulator.viewModel.Skill
import xyz.infiiinity.earnmoneysimulator.viewModel.Wallet
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
        Skill.load()
        PowerStation.load()
    }
}