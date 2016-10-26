package tech.viacom.junitvsspock

import com.google.common.collect.ImmutableCollection

interface ShipStore {
    void insert(Ship ship)

    ImmutableCollection<Ship> list()

    ImmutableCollection<Ship> findByAllegiance(String allegiance)

    Ship findByName(String name)
}
