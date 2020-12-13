package org.jaybaws.adventofcode.y2020.day12;

public class Right implements Move {

    private Integer degrees;

    public Right(Integer d) {
        this.degrees = d;
    }

    @Override
    public void doMove(Position p) {
        p.turnRight(degrees);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        waypoint.rotateRight(degrees);
    }

}