package tech.viacom.junitvsspock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class J04_ExceptionsTest {

    @Rule public ExpectedException thrown = ExpectedException.none();

    private List<String> list;

    @Test
    public void shouldThrow() {
        list = new ArrayList<>();

        list.add("foo");
        assertEquals("foo", list.get(0));

        thrown.expect(IndexOutOfBoundsException.class);
        list.get(1);
    }

    @Test
    public void shouldThrowAssertJ() {
        list = new ArrayList<>();

        assertThatThrownBy(() -> list.get(0))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index: 1, Size: 1");

        Throwable thrown = catchThrowable(() -> list.get(1));
        assertThat(thrown).hasMessage("Index: 1, Size: 1");
    }

    @Test
    public void testCreatingValidUrl() throws URISyntaxException {
        new URI("http://localhost:9090");
    }
}
