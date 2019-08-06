import java.util.ArrayList
import kotlin.collections.List

// ElectronicGizmo Interface
interface ElectronicGizmo {
    fun on()
    fun off()
}

// A Light, a type of ElectronicGizmo
class Light : ElectronicGizmo {
    override fun on() {
        println("Yo boss!  Just turned the light on!")
    }

    override fun off() {
        println("Boss man!  Makin' tings dark now!")
    }
}

// Command Interface
interface Command {
    fun execute()
}

// A Command: Light On
class LightOn(
        private val gizmo: ElectronicGizmo
) : Command {
    override fun execute() {
        gizmo.on()
    }
}

// A Command: Light Off
class LightOff(
        private val gizmo: ElectronicGizmo
) : Command {
    override fun execute() {
        gizmo.off()
    }
}

// A button for controlling commands
class ControllerButton(
        private val commandOne: Command,
        private val commandTwo: Command? = null
) {
    private var buttonState = true

    fun press() {
        commandOne.execute()
    }

    fun togglePress() {
        if (buttonState) {
            buttonState = !buttonState
            commandOne.execute()
        } else {
            buttonState = !buttonState
            if (commandTwo != null) {
                commandTwo.execute()
            }
        }
    }
}

// A Command: Turn all ElectronicGizmo's off
class MasterOffSwitch(
        private var gizmos : List<ElectronicGizmo>
) : Command {
    override fun execute() {
        for (gizmo in gizmos) {
            gizmo.off()
        }
    }
}

// A Command: Turn all ElectronicGizmo's on
class MasterOnSwitch(
        private var gizmos: List<ElectronicGizmo>
) : Command {
    override fun execute() {
        for (gizmo in gizmos) {
            gizmo.on()
        }
    }
}

// A HVAC System: Definitely a type of ElectronicGizmo
class HVAC : ElectronicGizmo {
    private var currentTempF = 72

    override fun on() {
        println("Environmental system turned on!")
        println("Temperature set to $currentTempF")
    }

    override fun off() {
        println("Environmental systems offline.")
    }

    fun tempUp() {
        currentTempF++
        println("Temperature set to $currentTempF")
    }
}

// A Command: Turn HVAC On
class HVACOn(
        private var gizmo: ElectronicGizmo
) : Command {
    override fun execute() {
        gizmo.on()
    }
}

// A Command: Turn HVAC Off
class HVACOff(
        private var gizmo: ElectronicGizmo
) : Command {
    override fun execute() {
        gizmo.off()
    }
}

// A Command: Turn HVAC Temp UP
class HVACTempUp(
        private var gizmo: HVAC
) : Command {
    override fun execute() {
        gizmo.tempUp()
    }
}



fun main (args: Array<String>) {

    /**
     * Setting up new button for Lights
     */
    val daddysNewLight = Light()
    val lightOnCommand = LightOn(daddysNewLight)
    val lightOffCommand = LightOff(daddysNewLight)
    var onPress = ControllerButton(lightOnCommand, lightOffCommand)

    /**
     * Press it
     */
    onPress.togglePress()
    onPress.togglePress()
    /**
     * Still gonna turn things on
     */
    onPress.press()

    /**
     * Maybe lets have more than one light in our house?
     */
    val batCaveLights = listOf(Light(), Light(), Light(), Light(), Light())

    /**
     *  Let's control all our lights at once
     */
    val allLights = listOf(daddysNewLight) + batCaveLights

    val masterLightOnCommand = MasterOnSwitch(allLights)
    val makeLight =  ControllerButton(masterLightOnCommand)
    makeLight.press()

    /**
     *  It's too bright, turn it all off
     */
    val masterLightOffCommand = MasterOffSwitch(allLights)
    val makeRealDark = ControllerButton(masterLightOffCommand)
    makeRealDark.press()

    /**
     * Need some climate control up in here
     */
    val climateModule = HVAC()
    val climateOnCommand = HVACOn(climateModule)
    val tempUpCommand = HVACTempUp(climateModule)
    val onButton = ControllerButton(climateOnCommand)
    onButton.press()

    /**
     * Get it turnt up in here
     */
    val tempUpButton = ControllerButton(tempUpCommand)
    tempUpButton.press()

    /**
     * Much too much, turn it all off
     */
    val allGizmos = allLights + listOf(climateModule)

    val killAllCommand = MasterOffSwitch(allGizmos)
    val KillAllButton = ControllerButton(killAllCommand)
    KillAllButton.press()

}