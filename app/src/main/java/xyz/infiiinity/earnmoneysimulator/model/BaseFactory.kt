package xyz.infiiinity.earnmoneysimulator.model

abstract class BaseFactory(resId:Int):BaseModel(resId) {
    abstract fun buy()

    abstract fun sell()
}