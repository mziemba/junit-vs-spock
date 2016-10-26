package tech.viacom.junitvsspock;

import com.google.common.collect.ImmutableCollection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class J00_TestStructureTest {

    @Test
    public void testInsertingShips() {
        final Ship ship1 = new Ship("Enterprise", "Federation");
        final Ship ship2 = new Ship("M'Char", "Klingon");
        final Ship ship3 = new Ship("Constitution", "Federation");
        final ShipStore shipStore = new MemoryShipStore();
        shipStore.insert(ship1);
        shipStore.insert(ship2);
        shipStore.insert(ship3);
        final ImmutableCollection<Ship> ships = shipStore.list();
        assertNotNull(ships);
        assertEquals(3, ships.size());
    }

    @Test
    public void testInsertingShipsRefactored() {
        // given
        final Ship ship1 = new Ship("Enterprise", "Federation");
        final Ship ship2 = new Ship("M'Char", "Klingon");
        final Ship ship3 = new Ship("Constitution", "Federation");
        final ShipStore shipStore = new MemoryShipStore();

        // when
        shipStore.insert(ship1);
        shipStore.insert(ship2);
        shipStore.insert(ship3);

        // then
        final ImmutableCollection<Ship> ships = shipStore.list();
        assertNotNull(ships);
        assertEquals(3, ships.size());
    }
}
