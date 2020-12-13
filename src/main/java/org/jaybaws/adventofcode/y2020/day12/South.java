package org.jaybaws.adventofcode.y2020.day12;

public class South implements Move {

    private Integer distance;

    public South(Integer d) {
        this.distance = d;
    }

    @Override
    public void doMove(Position p) {
         p.moveY(-1 * distance);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.moveY(-1 * distance);
    }

}