package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R
import xyz.infiiinity.earnmoneysimulator.utils.Resource
import xyz.infiiinity.earnmoneysimulator.utils.Tips.toast

object MiningLand : BaseFactory(R.array.mining) {

    const val FOSSIL = 0
    const val PRECIOUS_METAL = 1
    const val BASIC_METAL = 2
    const val LIGHT_METAL = 3

    override fun doEachSecond() {
        if(Plan.select.value== Plan.MINING) values[(0..lastIndex).random()].value++
        super.doEachSecond()
    }

    override fun buy() {
        if (Wallet.values[Wallet.CRYPTO].value < 200) toast(Resource.stringRes(R.string.no_item))
        else {
            Wallet.values[Wallet.CRYPTO].value -= 200
            RealEstate.values[RealEstate.MINING_LAND].value++
        }
    }

    override fun sell() {
        if (RealEstate.values[RealEstate.MINING_LAND].value <= 0) toast(Resource.stringRes(R.string.no_item))
        else {
            RealEstate.values[RealEstate.MINING_LAND].value--
            Wallet.values[Wallet.CRYPTO].value += 200
        }
    }

}