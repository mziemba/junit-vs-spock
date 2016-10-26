package tech.viacom.junitvsspock

import spock.lang.Specification

class S04_ExceptionsSpec extends Specification {

    def "test exceptions"() {
        given:
            def list = []

        when:
            list.add("foo")
        then:
            list.get(0) == "foo"

        when:
        list.get(1)
        then:
        thrown(IndexOutOfBoundsException)
    }

    def "test creating valid url"() {
        when:
        new URI("http://localhost")
        then:
        notThrown(URISyntaxException)
    }
}
