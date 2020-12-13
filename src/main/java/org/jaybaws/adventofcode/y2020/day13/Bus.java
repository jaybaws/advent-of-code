package org.jaybaws.adventofcode.y2020.day13;

public class Bus {

    private final Integer id;

    public Bus(String id) {
        this.id = Integer.valueOf(id);
    }

    public Integer waitTime(Integer timestamp) {
        return Math.floorMod(-1 * timestamp, id);
    }

    @Override
    public String toString() {
        return String.format("Bus[%d]", id);
    }

    public Integer getId() {
        return this.id;
    }

}