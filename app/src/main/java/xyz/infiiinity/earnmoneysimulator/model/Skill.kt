package xyz.infiiinity.earnmoneysimulator.model

import xyz.infiiinity.earnmoneysimulator.R

object Skill : BaseModel(R.array.skill) {

    override fun doEachSecond() {
        if(Plan.select.value== Plan.STUDY)values[(0..lastIndex).random()].value++
        super.doEachSecond()
    }

}