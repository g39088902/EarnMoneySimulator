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
        Wallet.load()
        Skill.load()
        PowerStation.load()
    }
}