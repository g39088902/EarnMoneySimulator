package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R

object RealEstate : BaseModel(R.array.real_estate) {
    const val AGRICULTURAL_LAND = 0
    const val MINING_LAND = 1
    const val POWER_STATION = 2
    const val RESIDENTIAL = 3
    const val SHOP = 4
    val factoryList = listOf(AgricultureLand,MiningLand,PowerStation,Residential,Shop)
}