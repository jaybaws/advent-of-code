package org.jaybaws.adventofcode.y2020.day12;

public class West implements Move {

    private Integer distance;

    public West(Integer d) {
        this.distance = d;
    }

    @Override
    public void doMove(Position p) {
        p.moveX(-1 * distance);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.moveX(-1 * distance);
    }

}