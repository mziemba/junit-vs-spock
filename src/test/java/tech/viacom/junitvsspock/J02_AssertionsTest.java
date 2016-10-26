package tech.viacom.junitvsspock;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class J02_AssertionsTest {

    private static final Ship SHIP_1 = new Ship("Enterprise", "Federation");
    private static final Ship SHIP_2 = new Ship("M'Char", "Klingon");
    private static final Ship SHIP_3 = new Ship("Constitution", "Federation");
    private static final List<Ship> SHIPS = Lists.newArrayList(SHIP_1, SHIP_2, SHIP_3);

    @Test
    public void junitAssertions() {
        final Adder adder = new Adder();
        final Multiplier multiplier = new Multiplier();

        assertEquals(20, multiplier.multiply(5, adder.add(2, 3)));
    }

    @Test
    public void betterAssertions() {
        //assertTrue("something".length() > 10);

        //assertThat("something".length()).isGreaterThan(10);

        assertThat(1 > 2).isTrue();
    }

    @Test
    public void aMoreRealisticExample() {
        final String text = "They are alone. They are a dying race.";

        assertEquals(5, new WordDetector().feedText(text).duplicatesFound().size());
        //assertThat(new WordDetector().feedText(text).duplicatesFound()).hasSize(5);
    }

    @Test
    public void functional() {
        SHIPS.forEach((Ship ship) -> assertThat(ship.getAllegiance()).isEqualTo("Federation"));
    }

    @Test
    public void assertJCollections() {
        assertThat(SHIPS).filteredOn("name", "Enterprise").containsOnly(SHIP_1);
        assertThat(SHIPS).filteredOn(new Predicate<Ship>() {
            @Override
            public boolean test(Ship ship) {
                return ship.getName().length() > 8;
            }
        }).containsOnly(SHIP_1, SHIP_3);

        assertThat(SHIPS).extracting("enteredService.value").containsOnly(2016, 2016, 2016);
    }

    // more can be found in AssertJ (http://joel-costigliola.github.io/assertj/)
}
