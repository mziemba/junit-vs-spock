package tech.viacom.junitvsspock

import com.google.common.collect.ImmutableCollection
import com.google.common.collect.ImmutableSet
import groovy.transform.CompileStatic

@CompileStatic
class MemoryShipStore implements ShipStore {

    @Delegate
    private final List<Ship> ships = []

    @Override
    void insert(Ship ship) {
        ships << ship
    }

    @Override
    ImmutableCollection<Ship> list() {
        ImmutableSet.copyOf(ships)
    }

    @Override
    ImmutableCollection<Ship> findByAllegiance(String allegiance) {
        ImmutableSet.copyOf ships.findAll { it.allegiance == allegiance }
    }

    @Override
    Ship findByName(String name) {
        ships.find { it.name == name }
    }
}