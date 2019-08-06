fun main (args: Array<String>) {
    val ON = "ON"
    val OFF = "OFF"

    fun controlLight(action : String) {
        if (action === ON) {
            println("Light on")
        } else if (action == OFF) {
            println("Light off")
        }
    }

    controlLight("ON")
    controlLight("OFF")

//    val fourLights = listOf("ON", "ON", "OFF", "ON")
//
//    fourLights.forEach{
//        controlLight(it)
//    }

}