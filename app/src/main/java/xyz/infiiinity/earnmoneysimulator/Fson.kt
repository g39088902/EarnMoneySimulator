package xyz.infiiinity.earnmoneysimulator

object Fson {
    fun getImages(payload:String):List<String>{
        val regex = Regex("(?<=\"image\":\").*?(?=\",\"seeds\")")
        val regexResult = regex.findAll(payload)
        val result = mutableListOf<String>()
        for(item in regexResult) {
            result.add(item.value)
        }
        return result
    }
}