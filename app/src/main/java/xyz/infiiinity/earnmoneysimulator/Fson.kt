package xyz.infiiinity.earnmoneysimulator

object Fson {
    fun getImagesFromJson(payload:String):List<String>{
        val regex = Regex("(?<=\"image\":\").*?(?=\",\"seeds\")")
        val regexResult = regex.findAll(payload)
        val result = mutableListOf<String>()
        for(item in regexResult) {
            result.add(item.value)
        }
        return result
    }

    fun getTokenFromHtml(payload: String):String{
        val regex = Regex("(?<=window.authToken = \").*?(?=\")")
        return regex.find(payload)?.value ?: ""
    }
}