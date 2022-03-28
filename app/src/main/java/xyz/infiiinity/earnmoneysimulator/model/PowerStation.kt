package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.MiningLand.BASIC_METAL
import xyz.infiiinity.earnmoneysimulator.model.MiningLand.FOSSIL
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.POWER_STATION
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast
import java.lang.Integer.max
import java.lang.Integer.min

object PowerStation : BaseFactory(R.array.power_station)  {
    const val ELECTRICITY = 0

    fun doEachMinute(){
        val generateAbility = 1 * RealEstate.values[POWER_STATION].value
        val generateActual = min(generateAbility, max(MiningLand.values[FOSSIL].value, 0))
        MiningLand.values[FOSSIL].value -= generateActual
        Wallet.values[ELECTRICITY].value += generateActual
    }

    override fun buy() {
        if (MiningLand.values[BASIC_METAL].value <= 100) toast(stringRes(R.string.no_item))
        else{
            MiningLand.values[BASIC_METAL].value -= 100
            RealEstate.values[POWER_STATION].value++
        }
    }

    override fun sell() {
        if (RealEstate.values[POWER_STATION].value <= 0) toast(stringRes(R.string.no_item))
        else{
            RealEstate.values[POWER_STATION].value--
            MiningLand.values[BASIC_METAL].value += 100
        }
    }
}