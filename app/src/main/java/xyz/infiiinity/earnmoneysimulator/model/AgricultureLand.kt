package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.utils.Resource
import xyz.infiiinity.earnmoneysimulator.utils.Tips
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

object AgricultureLand : BaseFactory(R.array.agriculture) {

    const val WHEAT = 0

    override fun doEachSecond() {
        if(Plan.select.value== Plan.FARMING) values[(0..lastIndex).random()].value++
        super.doEachSecond()
    }

    override fun buy() {
        if (Wallet.values[Wallet.CRYPTO].value < 200) toast(Resource.stringRes(R.string.no_item))
        else {
            Wallet.values[Wallet.CRYPTO].value -= 200
            RealEstate.values[RealEstate.AGRICULTURAL_LAND].value++
        }
    }

    override fun sell() {
        if (RealEstate.values[RealEstate.AGRICULTURAL_LAND].value <= 0) toast(Resource.stringRes(R.string.no_item))
        else {
            RealEstate.values[RealEstate.AGRICULTURAL_LAND].value--
            Wallet.values[Wallet.CRYPTO].value += 200
        }
    }

}