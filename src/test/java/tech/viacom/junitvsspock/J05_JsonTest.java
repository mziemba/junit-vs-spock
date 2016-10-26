package tech.viacom.junitvsspock;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

/**
 * We want to unit test a {@link PersonSerializer} class, that takes a {@link Person} instance and
 * serializes it to JSON.
 */
public class J05_JsonTest {

    private static final String LAST_NAME = "Doe";

    @Test
    public void shouldSerializePersonToJson() {
        // given
        final Person person = new Person("John", "Doe", "123123123", 25);
        final PersonSerializer serializer = new PersonSerializer();

        // when
        final String json = serializer.serialize(person);

        // then v1
        assertThatJson(json)
                .isEqualTo("{\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                        "\"phoneNumber\":\"123123123\",\"age\":25}");

        // then v2
        assertThatJson(json).isEqualTo(loadResource("json/expected.json"));

        // then v3
        assertThatJson(json)
                .isEqualTo("{\"firstName\":\"John\",\"lastName\":\"" + LAST_NAME + "\",\"phoneNumber\":\"123123123\",\"age\":25}");
    }

    private static String loadResource(String resourcePath) {
        try {
            final URL resource = Resources.getResource(resourcePath);
            return Resources.toString(resource, Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
