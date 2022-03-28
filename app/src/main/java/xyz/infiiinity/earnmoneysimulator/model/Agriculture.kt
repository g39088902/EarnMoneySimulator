package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R

object Agriculture : BaseModel(R.array.agriculture) {

    const val WHEAT = 0

    override fun doEachSecond() {
        if(Plan.select.value== Plan.FARMING) values[(0..lastIndex).random()].value++
        super.doEachSecond()
    }

}