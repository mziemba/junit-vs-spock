package tech.viacom.junitvsspock

import spock.lang.Specification

class S03_MockingSpec extends Specification {

    def publisher = new Publisher()
    def subscriber1 = Mock(Subscriber)
    def subscriber2 = Mock(Subscriber)

    void setup() {
        publisher.subscribers.addAll([ subscriber1, subscriber2 ])
    }

    def "should pass message to subscribers"() {
        when:
        publisher.send("hello")

        then:
        1 * subscriber1.receive("hello")
    }

    def "should receive messages in order"() {
        when:
        publisher.send("goodbye")
        publisher.send("hello")

        then:
        1 * subscriber1.receive("hello")
        then:
        1 * subscriber1.receive("goodbye")
    }

    def "returning values"() {
        given:
        subscriber1.receive(_ as String) >>> ["ok", "fail", "ok"] >> { throw new InternalError() } >> "ok"

        when:
        publisher.send("hello")
        publisher.send("hello")
        publisher.send("hello")
        //publisher.send("hello")
        then:
        thrown(InternalError)
    }
}
