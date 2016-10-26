package tech.viacom.junitvsspock

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class S01b_TestNamesSpec extends Specification {

    def "can tell if the string '#string' is an integer or not"() {
        expect:
        string.isInteger() == shouldBeInteger

        where:
        string | shouldBeInteger
        "ABC"  | false
        "123"  | true
        "1.2"  | false
        "1 2"  | false
        "1a2"  | false
    }

    @Unroll("can tell that the string '#string' is #description")
    def "identifies strings that are integers"() {
        expect:
        string.isInteger() == shouldBeInteger

        where:
        string | shouldBeInteger
        "ABC"  | false
        "123"  | true
        "1.2"  | false
        "1 2"  | false
        "12a"  | false

        description = shouldBeInteger ? "an integer" : "not an integer"
    }
}
