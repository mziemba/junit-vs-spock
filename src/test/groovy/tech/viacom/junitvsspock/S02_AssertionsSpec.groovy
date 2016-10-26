package tech.viacom.junitvsspock

import spock.lang.Specification
import spock.lang.Subject

import java.time.Year

class S02_AssertionsSpec extends Specification {

    @Subject def ships = new MemoryShipStore()

    def setup() {
        ships << new Ship("Gr'oth", "Klingon")
        ships << new Ship("Enterprise", "Federation")
        ships << new Ship("Constitution", "Federation")
        ships << new Ship("Constellation", "Federation")
        ships << new Ship("M'Char", "Klingon")
        ships << new Ship("Haakona", "Romulan")
    }

    def "spock assertions"() {
        given:
            def adder = new Adder()
            def multiplier = new Multiplier()

        expect:
            multiplier.multiply(5, adder.add(2, 3)) == 20
    }

    def "both sides of assert are analyzed"() {
        expect:
            (4 * 15) - (4 + 4) == (2 * 30) - 9
    }

    def "a more realistic example"() {
        given: "some text"
            def text = "They are alone. They are a dying race."
        expect: "the number of duplicates in the text is equal to expected"
            new WordDetector().feedText(text).duplicatesFound().size() == 5
    }

    def "can find ships by allegiance"() {
        // use functional Groovy
        when:
            def results = ships.list()

        then:
            results.allegiance.every {
                it == "Federation"
            }
    }

    def "grouping assertions"() {
        when:
            def ship = ships.findByName("Enterprise")

        then:
        assertShip(ship)
    }

    void assertShip(Ship ship) {
        assert ship.name == "Enterpr"
        assert ship.allegiance == "Federation"
        assert ship.enteredService == Year.of(2016)
    }
}
