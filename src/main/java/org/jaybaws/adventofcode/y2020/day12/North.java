package org.jaybaws.adventofcode.y2020.day12;

public class North implements Move {

    private Integer distance;

    public North(Integer d) {
        this.distance = d;
    }

    @Override
    public void doMove(Position p) {
        p.moveY(distance);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.moveY(distance);
    }

}