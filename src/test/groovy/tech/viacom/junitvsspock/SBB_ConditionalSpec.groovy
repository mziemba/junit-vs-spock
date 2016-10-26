package tech.viacom.junitvsspock

import spock.lang.IgnoreIf
import spock.lang.Specification

import static java.net.HttpURLConnection.HTTP_OK

/**
 * We can ignore tests based on some condition (for example tests that require SMTP server configured).
 *
 * {@see http://spockframework.org/spock/docs/1.0/extensions.html}
 */
class SBB_ConditionalSpec extends Specification {

    private static final String URL = "http://localhost:90"

    @IgnoreIf({ !Network.isReachable(URL) })
    def "requires network 1"() {
        given:
        HttpURLConnection connection = URL.toURL().openConnection() as HttpURLConnection

        when:
        connection.connect()
        println("running")

        then:
        connection.responseCode == HTTP_OK
    }

    @IgnoreIf({ javaVersion == 1.7 })
    def "run on specific java version"() {
        expect:
        true
    }

    // Ignore, IgnoreRest, Require, Stepwise, Timeout
}
