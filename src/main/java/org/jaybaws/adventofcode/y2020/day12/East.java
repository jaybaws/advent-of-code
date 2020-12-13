package org.jaybaws.adventofcode.y2020.day12;

public class East implements Move {

    private Integer distance;

    public East(Integer d) {
        this.distance = d;
    }

    @Override
    public void doMove(Position p) {
        p.moveX(distance);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.moveX(distance);
    }

}