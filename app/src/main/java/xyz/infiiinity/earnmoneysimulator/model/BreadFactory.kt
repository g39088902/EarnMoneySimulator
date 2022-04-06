package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.AgricultureLand.WHEAT
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.BREAD_FACTORY
import xyz.infiiinity.earnmoneysimulator.model.Wallet.CRYPTO
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast
import java.lang.Integer.max
import java.lang.Integer.min

object BreadFactory : BaseFactory(R.array.bread_factory)  {
    const val BREAD = 0

    fun doEachMinute(){
        val produceAbility = 1 * RealEstate.values[BREAD_FACTORY].value
        val produceActual = min(produceAbility, max(AgricultureLand.values[WHEAT].value, 0))
        AgricultureLand.values[WHEAT].value -= produceActual
        values[BREAD].value += produceActual
    }

    override fun buy() {
        if (Wallet.values[CRYPTO].value <= 100) toast(stringRes(R.string.no_item))
        else{
            Wallet.values[CRYPTO].value -= 100
            RealEstate.values[BREAD_FACTORY].value++
        }
    }

    override fun sell() {
        if (RealEstate.values[BREAD_FACTORY].value <= 0) toast(stringRes(R.string.no_item))
        else{
            RealEstate.values[BREAD_FACTORY].value--
            Wallet.values[CRYPTO].value += 100
        }
    }
}