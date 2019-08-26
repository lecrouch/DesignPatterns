interface Command {
    fun land()
}

interface IATCMediator {
    val canHazLandOkay: Boolean

    fun registerYerselfARunway(runway: Runway)
    fun registerYerselfAFlight(flight: Flight)
    fun setLandingStatus(status: Boolean)
}

class ATCMediator: IATCMediator {
    private var flight: Flight? = null
    private var runway: Runway? = null

    override var canHazLandOkay = false

    override fun registerYerselfARunway (runway: Runway) {
        this.runway = runway
    }

    override fun registerYerselfAFlight (flight: Flight) {
        this.flight = flight
    }

    override fun setLandingStatus (status: Boolean) {
        canHazLandOkay = status
    }
}

class Runway (private val atcMediator: IATCMediator) : Command {
    init {
        atcMediator.setLandingStatus(true)
    }

    override fun land() {
        println("You can haz landing")
        atcMediator.setLandingStatus(true)
    }
}

class Flight(private val atcMediator: IATCMediator) : Command {
    override fun land() {
        if (atcMediator.canHazLandOkay) {
            println("Landing Done")
            atcMediator.setLandingStatus(true)
        } else {
            println("gotta wait bruh")
        }
    }

    fun getReady() {
        println("gettnin rdy")
    }
}

fun main (args: Array<String>) {
    val atcMediator = ATCMediator()
    val stupidPlane = Flight(atcMediator)
    val aRunway = Runway(atcMediator)
    atcMediator.registerYerselfAFlight(stupidPlane)
    atcMediator.registerYerselfARunway(aRunway)

    stupidPlane.getReady()
    aRunway.land()
    stupidPlane.land()
}

