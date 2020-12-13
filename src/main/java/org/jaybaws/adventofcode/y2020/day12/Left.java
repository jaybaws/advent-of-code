package org.jaybaws.adventofcode.y2020.day12;

public class Left implements Move {

    private Integer degrees;

    public Left(Integer d) {
        this.degrees = d;
    }

    @Override
    public void doMove(Position p) {
        p.turnLeft(degrees);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.rotateLeft(degrees);
    }

}