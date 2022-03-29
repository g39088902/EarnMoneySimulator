package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.model.MiningLand.BASIC_METAL
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.POWER_STATION
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.RESIDENTIAL
import xyz.infiiinity.earnmoneysimulator.model.RealEstate.SHOP
import xyz.infiiinity.earnmoneysimulator.model.Wallet.CRYPTO
import xyz.infiiinity.earnmoneysimulator.utils.Resource.stringRes
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

object Residential : BaseFactory(R.array.shop)  {

    fun doEachMinute(){
        //Wallet.values[CRYPTO].value += RealEstate.values[SHOP].value
    }

    override fun buy() {
        if (Wallet.values[CRYPTO].value <= 100) toast(stringRes(R.string.no_item))
        else{
            Wallet.values[CRYPTO].value -= 100
            RealEstate.values[RESIDENTIAL].value++
        }
    }

    override fun sell() {
        if (RealEstate.values[RESIDENTIAL].value <= 0) toast(stringRes(R.string.no_item))
        else{
            RealEstate.values[RESIDENTIAL].value--
            Wallet.values[CRYPTO].value += 100
        }
    }
}