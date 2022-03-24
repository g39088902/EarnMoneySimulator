package xyz.infiiinity.earnmoneysimulator.utils

import xyz.infiiinity.earnmoneysimulator.CommonApplication

object Resource {
    fun stringRes(id:Int) = CommonApplication.context.resources.getString(id)
    fun stringRes(id:Int,index:Int) = CommonApplication.context.resources.getStringArray(id)[index]
}