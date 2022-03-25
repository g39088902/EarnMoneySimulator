package xyz.infiiinity.earnmoneysimulator.utils

import xyz.infiiinity.earnmoneysimulator.CommonApplication.Companion.context

object Resource {
    fun stringRes(id:Int) = context.resources.getString(id)
    fun stringRes(id:Int,index:Int) = context.resources.getStringArray(id)[index]
    fun ResourceLastIndex(id:Int) = context.resources.getStringArray(id).lastIndex
}