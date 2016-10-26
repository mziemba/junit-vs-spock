package tech.viacom.junitvsspock

import spock.lang.Specification
import spock.lang.Subject

class S00a_TestStructureSpec extends Specification {

    def "test inserting ships"() {
        given: "some ships"
        def ship1 = new Ship("Enterprise", "Federation")
        def ship2 = new Ship("M'Char", "Klingon")
        def ship3 = new Ship("Constitution", "Federation")

        and: "an instance of ship store"
        @Subject def shipStore = new MemoryShipStore()

        expect: "the store is initially empty"
        shipStore.list().size() == 0

        when: "we insert some ships"
        shipStore.insert(ship1)
        shipStore.insert(ship2)
        shipStore.insert(ship3)

        then: "we can get them back"
        def ships = shipStore.list()
        ships.size() == 3
    }
}
