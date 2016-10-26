package tech.viacom.junitvsspock

import spock.lang.Specification

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson

class S05_JsonSpec extends Specification {

    private static final String LAST_NAME = "Doe"
    private static final String EXPECTED_JSON = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"phoneNumber\":\"123123123\",\"age\":25}"
    private static final String EXPECTED_JSON_2 = """
    {
        "firstName":"John",
  "lastName":${LAST_NAME},
  "phoneNumber":"123123123",
  "age":25
}
    """

    def "should serialize person to JSON"() {
        given:
            PersonSerializer serializer = new PersonSerializer()
            Person person = new Person("John", "Doe", "123123123", 25)

        when:
            def json = serializer.serialize(person)

        then:
            assertThatJson(json).isEqualTo(EXPECTED_JSON_2)
    }
}
